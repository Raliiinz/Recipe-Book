package com.example.recipebook.data.mapper

import com.example.recipebook.data.local.database.entities.UserEntity

fun toUserEntity(phone: String, password: String): UserEntity {
    return UserEntity(
        phone = phone,
        password = password
    )
}
