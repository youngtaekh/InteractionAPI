package com.young.interaction.controller

import com.young.interaction.model.UserModel
import com.young.interaction.model.response.EmptyBody
import com.young.interaction.model.response.Response
import com.young.interaction.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/user"])
class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping
    fun setUser(@RequestBody userModel: UserModel): ResponseEntity<Any> {
        println("POST an user")
        if (userModel.userId.isNullOrEmpty()) return Response.error("userId is empty", userModel)

        val user = userService.getUserByUserId(userId = userModel.userId)
        if (user.isPresent) return Response.error("User Already Exist", userModel)

        val result = userService.saveUser(userModel)
        return Response.success(result)
    }

    @GetMapping
    fun getUser(@RequestParam(required = false) userId: String?): ResponseEntity<Any> {
        return if (userId == null) {
            println("GET user")
            val users = userService.getAllUser()
            Response.success(users)
        } else {
            println("GET user $userId")
            val user = userService.getUserByUserId(userId)
            if (user.isPresent) Response.success(user)
            else Response.error("No User", EmptyBody())
        }
    }

    @PutMapping("/sign")
    fun signInUser(@RequestBody model: UserModel): ResponseEntity<Any> {
        println("sign user")
        if (model.userId.isNullOrEmpty()) return Response.error("userId is empty", model)
        if (model.password.isNullOrEmpty()) return Response.error("password is empty", EmptyBody())

        val user = userService.getUserByUserId(model.userId)
        if (!user.isPresent) return Response.error("No user", model)
        if (user.get().password != model.password) return Response.error("wrong password", model)

        return Response.success(user)
    }

    @GetMapping("/{userName}")
    fun getUserByName(@PathVariable userName: String): ResponseEntity<Any> {
        println("GET user by user name: $userName")
        val users = userService.getUsersByUserName(userName)
        return Response.success(users)
    }

    @PutMapping("/name")
    fun updateUserName(@RequestBody userModel: UserModel?): ResponseEntity<Any> {
        println("PUT user name")
        if (userModel == null || userModel.userId.isNullOrEmpty() || userModel.name.isNullOrEmpty()) {
            return Response.error("No Param", userModel)
        }
        val user = userService.updateUserName(userId = userModel.userId, name = userModel.name!!)
        return Response.success(user)
    }

    @PutMapping("/email")
    fun updateUserEmail(@RequestBody userModel: UserModel?): ResponseEntity<Any> {
        if (userModel == null || userModel.userId.isNullOrEmpty() || userModel.email.isNullOrEmpty()) {
            return Response.error("No Param", userModel)
        }
        println("PUT user email ${userModel.email}")
        val user = userService.updateUserEmail(userId = userModel.userId, email = userModel.email!!)
        return Response.success(user)
    }

    @PutMapping("/password")
    fun updateUserPassword(@RequestBody userModel: UserModel?): ResponseEntity<Any> {
        if (userModel == null || userModel.userId.isNullOrEmpty() || userModel.password.isNullOrEmpty()) {
            return Response.error("No Param", userModel)
        }
        println("PUT user password ${userModel.password}")
        val user = userService.updateUserPassword(userId = userModel.userId, password = userModel.password!!)
        return Response.success(user)
    }

    @DeleteMapping
    fun deleteUser(@RequestParam(required = false) userId: String?): ResponseEntity<Any> {
        if (userId.isNullOrEmpty()) {
            println("DELETE all user")
            userService.removeAllUser()
        } else {
            println("DELETE user $userId")
            userService.removeUser(userId)
        }
        return Response.success("OK")
    }
}
