package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)