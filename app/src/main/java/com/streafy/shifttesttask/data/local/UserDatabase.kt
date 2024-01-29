package com.streafy.shifttesttask.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 2,
    autoMigrations = [AutoMigration (from = 1, to = 2)]
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}