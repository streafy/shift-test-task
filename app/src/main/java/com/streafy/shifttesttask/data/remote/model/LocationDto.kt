package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String
)