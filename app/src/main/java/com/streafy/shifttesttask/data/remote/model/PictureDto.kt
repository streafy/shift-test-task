package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureDto(
    @SerialName("large")
    val pictureUri: String
)