package com.annotation.flea.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

/**
 * Mapping for DB view
 */
// 배송지 주소 검색 위한 View 엔티티
@Immutable
@Table(name = "address_view")
@Entity
open class AddressView protected constructor() {
    @Column(name = "postal_code", length = 5)
    open var postalCode: String? = null
        protected set

    @Id
    @Column(name = "road_name_mgmt_no", length = 26)
    open var roadNameMgmtNo: String? = null
        protected set

    @Column(name = "sido_name", length = 40)
    open var sidoName: String? = null
        protected set

    @Column(name = "sigungu_name", length = 40)
    open var sigunguName: String? = null
        protected set

    @Column(name = "legal_eupmyeondong_name", length = 40)
    open var legalEupmyeondongName: String? = null
        protected set

    @Column(name = "legal_ri_name", length = 40)
    open var legalRiName: String? = null
        protected set

    @Column(name = "road_name", length = 80)
    open var roadName: String? = null
        protected set

    @Column(name = "underground_status", length = Integer.MAX_VALUE)
    open var undergroundStatus: String? = null
        protected set

    @Column(name = "building_main_number")
    open var buildingMainNumber: Int? = null
        protected set

    @Column(name = "building_sub_number")
    open var buildingSubNumber: Int? = null
        protected set

    @Column(name = "lot_main_number")
    open var lotMainNumber: Int? = null
        protected set

    @Column(name = "lot_sub_number")
    open var lotSubNumber: Int? = null
        protected set

    @Column(name = "sigungu_building_name", length = 400)
    open var sigunguBuildingName: String? = null
        protected set

    @Column(name = "apt_type", length = Integer.MAX_VALUE)
    open var aptType: String? = null
        protected set
}