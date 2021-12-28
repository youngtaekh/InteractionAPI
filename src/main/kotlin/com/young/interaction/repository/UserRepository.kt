package com.young.interaction.repository

import com.young.interaction.model.UserModel
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository: CrudRepository<UserModel, Int> {
    fun findAllBy(): List<UserModel>
    fun findUserModelByUserId(userId: String): Optional<UserModel>
    fun findUserModelsByNameContains(name: String): List<UserModel>
    fun removeUserModelByUserId(userId: String): Any?
    fun deleteUserModelByUserId(userId: String): Any?
    fun removeAllBy()
}
