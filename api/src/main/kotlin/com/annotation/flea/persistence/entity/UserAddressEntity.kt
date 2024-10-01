package com.annotation.flea.persistence.entity

import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import java.util.*

//유저가 저장한 배송지 주소
@Entity
@Table(name="user_address")
class UserAddressEntity(

    @Id
    @Column(name="seq")
    val seq: UUID = Generators.timeBasedEpochGenerator().generate(),

    @ManyToOne
    @JoinColumn(name="user_id")
    val userId: UserEntity,

    @Column(name = "road_address")
    val roadAddress: String,
    @Column(name = "lot_address")
    val lotAddress: String,
    @Column(name = "detail_address")
    val detailAddress: String,
)