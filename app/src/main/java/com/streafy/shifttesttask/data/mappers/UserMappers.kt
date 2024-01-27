package com.streafy.shifttesttask.data.mappers

import com.streafy.shifttesttask.data.remote.model.LocationDto
import com.streafy.shifttesttask.data.remote.model.UserDto
import com.streafy.shifttesttask.domain.entity.User

fun UserDto.toUser() =
    User(
        firstName = name.first,
        lastName = name.last,
        photoUri = picture.pictureUri,
        address = location.toAddressString(),
        phoneNumber = phone
    )

private fun LocationDto.toAddressString() =
    "$city, $country"