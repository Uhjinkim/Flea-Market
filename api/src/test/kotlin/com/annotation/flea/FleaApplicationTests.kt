package com.annotation.flea

import com.annotation.flea.persistence.repository.AddressViewRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FleaApplicationTests(
    @Autowired private val addressRepository: AddressViewRepository
) {

    @Test
    fun contextLoads() {
        val query = "용산구 한강대로"
        val parts = query.split(" ")
        val lastPart = parts.last().split("-")
        var sido = ""
        var sigungu = ""
        var eupmyeondong = ""
        var ri = ""
        var lotMainNumber = 0
        var lotSubNumber = 0
        var roadName = ""
        var isNumberExist = false
        var mainNumber = 0
        var subNumber = 0
        if(lastPart[0].toIntOrNull() != null) {
            //건물번호 있을 때 저장
            mainNumber = lastPart[0].toInt()
            subNumber = if(lastPart.size > 1) lastPart[1].toInt() else 0
            isNumberExist = true
        }
        parts.forEachIndexed { index, part ->
            if (part.endsWith("길") || part.endsWith("로")) {
                // 도로명 있을 때. 건물번호 저장했으므로 다른 검색어 필요 없음
                roadName = part
            } else {
                //도로명 없을 때
                if (part.endsWith("동") || part.endsWith("읍") || part.endsWith("면")) {
                    // 읍면동 있을 때
                    eupmyeondong = part
                }
                if (part.endsWith("도")) {
                    // 시도 중에 도 있을 때
                    sido = part
                }
                if (part.endsWith("군") || part.endsWith("구")) {
                    // 시군구 중에 군, 구 있을 때 저장
                    sigungu = part
                    // 앞에 시도 있으면 저장
                    if (index > 0) sido = parts[index - 1]
                }
                if (part.endsWith("리")) {
                    // 리 있을 때 저장
                    ri = part
                }
                if (isNumberExist) { // 도로명 없고, 건물번호 있을 때
                    // 지번 건물번호도 포함
                    lotMainNumber = mainNumber
                    lotSubNumber = subNumber
                }
            }
            if(roadName.isEmpty() && eupmyeondong.isEmpty() && sido.isEmpty() && sigungu.isEmpty() && ri.isEmpty() && lotMainNumber == 0 && lotSubNumber == 0) {
                // 모든 정보가 없을 때
                roadName = part
            }
        }

        val addressList = if(roadName.isNotEmpty()) {
            addressRepository.findByRoadAddr(sido, sigungu, roadName, mainNumber, subNumber)
        } else {
            addressRepository.findByLotAddr(sido, sigungu, eupmyeondong, ri, lotMainNumber, lotSubNumber)
        }
        addressList.forEach {
            println("도로명: (${it.postalCode}) ${it.sidoName} ${it.sigunguName} ${it.roadName} ${ if(it.undergroundStatus == "1") "(지하)" else "" } ${it.buildingMainNumber}${if(it.buildingSubNumber == 0) "" else "-"+ it.buildingSubNumber} ${if(it.aptType=="1") it.sigunguBuildingName else ""} ${if(it.legalRiName == null) it.legalEupmyeondongName else "" }")
            println("지번: (${it.postalCode}) ${it.sidoName} ${it.sigunguName} ${it.legalEupmyeondongName} ${if(it.legalRiName == null) "" else it.legalRiName} ${it.lotMainNumber}${if(it.lotSubNumber == 0) "" else "-"+it.lotSubNumber}")
        }
    }

}
