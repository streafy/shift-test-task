package com.streafy.shifttesttask.domain.entity

data class UserWithDetails(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photoUri: String,
    val address: String,
    val phoneNumber: String,
    val dateOfBirth: String,
    val registered: String,
    val gender: String
)
