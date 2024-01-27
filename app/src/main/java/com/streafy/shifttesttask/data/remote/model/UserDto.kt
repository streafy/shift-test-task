package com.streafy.shifttesttask.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    @SerialName("dob") val dateOfBirth: DateWithAgeDto,
    val registered: DateWithAgeDto,
    val phone: String,
    val picture: PictureDto
)

