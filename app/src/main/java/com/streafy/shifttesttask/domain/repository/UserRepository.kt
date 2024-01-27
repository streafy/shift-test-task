package com.streafy.shifttesttask.domain.repository

import com.streafy.shifttesttask.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>
}