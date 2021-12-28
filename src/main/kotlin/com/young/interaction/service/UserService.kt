package com.young.interaction.service

import com.young.interaction.model.UserModel
import org.springframework.stereotype.Service
import java.util.*

@Service
interface UserService {
    fun getAllUser(): List<UserModel>
    fun saveUser(userModel: UserModel): Optional<UserModel>
    fun getUserByUserId(userId: String): Optional<UserModel>
    fun getUsersByUserName(name: String): List<UserModel>
    fun updateUserName(userId: String, name: String): Optional<UserModel>
    fun updateUserPassword(userId: String, password: String): Optional<UserModel>
    fun updateUserEmail(userId: String, email: String): Optional<UserModel>
    fun removeUser(userId: String)
    fun removeAllUser()
}
