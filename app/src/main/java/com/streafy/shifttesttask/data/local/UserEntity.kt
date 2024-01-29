package com.streafy.shifttesttask.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val gender: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val location: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String,
    val registered: String,
    val phone: String,
    @ColumnInfo(name = "photo_uri") val pictureUri: String,
    @ColumnInfo(defaultValue = "") val email: String
)