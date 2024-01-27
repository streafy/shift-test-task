package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class DateWithAgeDto(
    val date: String,
    val age: Int
)