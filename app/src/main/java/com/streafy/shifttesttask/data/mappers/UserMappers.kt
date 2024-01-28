package com.streafy.shifttesttask.data.mappers

import com.streafy.shifttesttask.data.local.UserEntity
import com.streafy.shifttesttask.data.remote.model.LocationDto
import com.streafy.shifttesttask.data.remote.model.RemoteResponse
import com.streafy.shifttesttask.data.utils.Config
import com.streafy.shifttesttask.domain.entity.User


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
            pictureUri = userDto.picture.pictureUri
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

private fun LocationDto.toAddressString() =
    "$city, $country"