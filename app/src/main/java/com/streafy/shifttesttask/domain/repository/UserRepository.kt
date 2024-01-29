package com.streafy.shifttesttask.domain.repository

import androidx.paging.PagingData
import com.streafy.shifttesttask.domain.entity.User
import com.streafy.shifttesttask.domain.entity.UserWithDetails
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<PagingData<User>>

    fun getUserById(id: Int): Flow<UserWithDetails>
}