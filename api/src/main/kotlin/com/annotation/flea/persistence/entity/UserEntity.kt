package com.annotation.flea.persistence.entity

import com.fasterxml.uuid.Generators
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name="userinfo")
class UserEntity(
    //회원 고유번호 UUID v7
    @Id
    @Column(name = "user_id")
    val id : UUID = Generators.timeBasedEpochGenerator().generate(),

    // 회원 로그인 정보: 아이디, 비밀번호
    val username: String,
    val password: String,

    //회원 정보: 이메일, 이름, 닉네임
    val email: String,
    val name : String, // real name
    val nickname: String,

    //회원 연락처 정보: 휴대폰 번호 11자 제한
    val phone: String,

    //회원 권한 정보: 일반 사용자, 관리자
    val role: String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    //회원 프로필 이미지 정보: 이미지 URL
    @OneToOne
    @JoinColumn(name = "profile_image")
    var profileImage: ProfileImageEntity? = null
}