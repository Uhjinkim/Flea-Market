package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.AddressView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AddressViewRepository : JpaRepository<AddressView, String> {
    @Query("""
    SELECT a
    FROM AddressView a
    WHERE (a.sidoName like concat('%', :sido, '%'))
        AND (a.sigunguName like concat('%', :sigungu, '%'))
        AND (a.roadName like concat('%', :roadName, '%'))
        AND (a.buildingMainNumber = :buildingMainNumber)
        AND (a.buildingSubNumber = :buildingSubNumber)
            """)
    fun findByRoadAddr(@Param("sido") sido: String,
                       @Param("sigungu") sigungu: String,
                       @Param("roadName") roadName: String,
                       @Param("buildingMainNumber") buildingMainNumber: Int,
                       @Param("buildingSubNumber") buildingSubNumber: Int,
                       ): List<AddressView>

    @Query("""
        SELECT a 
        FROM AddressView a
        WHERE (a.sidoName like concat('%', :sido, '%'))
            AND (a.sigunguName like concat('%', :sigungu, '%'))
            AND (a.legalEupmyeondongName like concat('%', :emd, '%'))
            AND (a.legalRiName like concat('%', :ri, '%'))
            AND (a.lotMainNumber = :lotMainNumber)
            AND (a.lotSubNumber = :lotSubNumber)
    """)
    fun findByLotAddr(@Param("sido") sido: String,
                        @Param("sigungu") sigungu: String,
                        @Param("emd") eupmyeondong: String,
                        @Param("ri") ri: String,
                        @Param("lotMainNumber") lotMainNumber: Int,
                        @Param("lotSubNumber") lotSubNumber: Int,
                      ): List<AddressView>
}
