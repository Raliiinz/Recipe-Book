package com.example.recipebook.data.local.repository

import com.example.recipebook.data.local.database.dao.UserDao
import com.example.recipebook.data.mapper.toUserEntity
import com.example.recipebook.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun registerUser(phone: String, password: String) {
        val userEntity = toUserEntity(phone, password)
        userDao.insertUser(userEntity)
    }

    override suspend fun isUserExists(phone: String): Boolean {
        return userDao.getUserByPhone(phone) != null
    }

    override suspend fun login(phone: String, password: String): Boolean {
        val user = userDao.getUserByPhone(phone)
        return user?.password == password
    }
}
