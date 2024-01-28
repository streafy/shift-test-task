package com.streafy.shifttesttask.domain.entity

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photoUri: String,
    val address: String,
    val phoneNumber: String
)