package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.CreateUserPort
import com.annotation.flea.application.port.out.LoadUserPort
import com.annotation.flea.domain.entity.User
import com.annotation.flea.persistence.mapper.out.UserMapper
import com.annotation.flea.persistence.repository.UserRepository
import org.springframework.stereotype.Repository


@Repository
class UserEntityAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) : CreateUserPort, LoadUserPort {
    override fun createUser(user: User): Boolean{
        val isExist = userRepository.existsByUsername(user.username)
        if(isExist) {
            println("사용자가 이미 존재합니다")
            return false
        }
        val entity = userMapper.mapToUserEntity(user)
        userRepository.save(entity)
        return true
    }

    override fun loadUserByUsername(username: String): User? {
        val userEntity = userRepository.findByUsername(username)
        if(userEntity == null) {
            println("해당 사용자를 불러올 수 없습니다")
            return null
        }
        return userMapper.mapToUser(userEntity)
    }
}