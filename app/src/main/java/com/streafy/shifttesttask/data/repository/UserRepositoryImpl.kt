package com.streafy.shifttesttask.data.repository

import com.streafy.shifttesttask.data.mappers.toUser
import com.streafy.shifttesttask.data.remote.UserApi
import com.streafy.shifttesttask.domain.entity.User
import com.streafy.shifttesttask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override fun getUsers(): Flow<List<User>> = flow {
        val users = api.getUsers(1, 10).results.map { it.toUser() }
        emit(users)
    }
}