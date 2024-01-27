package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class NameDto(
    val title: String,
    val first: String,
    val last: String
)