package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteResponse(
    val results: List<UserDto>,
    val info: InfoDto
)