package com.streafy.shifttesttask.data.mappers

import com.streafy.shifttesttask.data.local.UserEntity
import com.streafy.shifttesttask.data.remote.model.LocationDto
import com.streafy.shifttesttask.data.remote.model.RemoteResponse
import com.streafy.shifttesttask.data.utils.Config
import com.streafy.shifttesttask.domain.entity.User
import com.streafy.shifttesttask.domain.entity.UserWithDetails
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun RemoteResponse.toUserEntityList() =
    results.mapIndexed { index, userDto ->
        val page = info.page
        val userId = (page - 1) * Config.ITEMS_PER_PAGE + index

        UserEntity(
            id = userId,
            gender = userDto.gender,
            firstName = userDto.name.first,
            lastName = userDto.name.last,
            location = userDto.location.toAddressString(),
            dateOfBirth = userDto.dateOfBirth.date,
            registered = userDto.registered.date,
            phone = userDto.phone,
            pictureUri = userDto.picture.pictureUri,
            email = userDto.email
        )
    }

fun UserEntity.toUser() =
    User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        photoUri = pictureUri,
        address = location,
        phoneNumber = phone
    )

fun UserEntity.toUserWithDetails() =
    UserWithDetails(
        id = id,
        firstName = firstName,
        lastName = lastName,
        photoUri = pictureUri,
        address = location,
        phoneNumber = phone,
        dateOfBirth = LocalDateTime.parse(dateOfBirth, formatter).toDateString(),
        registered = LocalDateTime.parse(registered, formatter).toDateString(),
        gender = gender,
        email = email
    )

private fun LocationDto.toAddressString() =
    "$city, $country"

private fun LocalDateTime.toDateString() =
    "$dayOfMonth.$monthValue.$year"

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
