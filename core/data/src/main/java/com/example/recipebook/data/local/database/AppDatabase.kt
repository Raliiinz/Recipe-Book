package com.example.recipebook.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipebook.data.local.database.entities.UserEntity
import com.example.recipebook.data.local.database.dao.UserDao

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DB_LOG_KEY = "AppDatabase"
    }
}
