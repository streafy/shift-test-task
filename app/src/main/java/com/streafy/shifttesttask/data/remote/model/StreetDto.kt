package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class StreetDto(
    val number: Int,
    val name: String
)