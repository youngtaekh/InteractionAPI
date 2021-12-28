package com.young.interaction.service

import com.young.interaction.model.UserModel
import com.young.interaction.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class UserServiceImpl: UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun getAllUser(): List<UserModel> {
        return userRepository.findAllBy()
    }

    @Transactional
    override fun saveUser(userModel: UserModel): Optional<UserModel> {
        userRepository.save(userModel)
        return userRepository.findUserModelByUserId(userModel.userId!!)
    }

    override fun getUserByUserId(userId: String): Optional<UserModel> {
        return userRepository.findUserModelByUserId(userId)
    }

    override fun getUsersByUserName(name: String): List<UserModel> {
        return userRepository.findUserModelsByNameContains(name)
    }

    override fun updateUserName(userId: String, name: String): Optional<UserModel> {
        val user = userRepository.findUserModelByUserId(userId)
        if (user.isPresent) {
            user.get().name = name
            userRepository.save(user.get())
        }
        return user
    }

    override fun updateUserPassword(userId: String, password: String): Optional<UserModel> {
        val user = userRepository.findUserModelByUserId(userId)
        if (user.isPresent) {
            user.get().password = password
            userRepository.save(user.get())
        }
        return user
    }

    override fun updateUserEmail(userId: String, email: String): Optional<UserModel> {
        val user = userRepository.findUserModelByUserId(userId)
        if (user.isPresent) {
            user.get().email = email
            userRepository.save(user.get())
        }
        return user
    }

    @Transactional
    override fun removeUser(userId: String) {
        userRepository.deleteUserModelByUserId(userId)
    }

    @Transactional
    override fun removeAllUser() {
        userRepository.removeAllBy()
    }
}
