package com.streafy.shifttesttask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.streafy.shifttesttask.data.local.UserDatabase
import com.streafy.shifttesttask.data.local.UserEntity
import com.streafy.shifttesttask.data.mappers.toUser
import com.streafy.shifttesttask.data.mappers.toUserWithDetails
import com.streafy.shifttesttask.domain.entity.User
import com.streafy.shifttesttask.domain.entity.UserWithDetails
import com.streafy.shifttesttask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val pager: Pager<Int, UserEntity>,
    private val database: UserDatabase
) : UserRepository {

    override fun getUsers(): Flow<PagingData<User>> =
        pager.flow
            .map { pagingData ->
                pagingData.map { userEntity -> userEntity.toUser() }
            }

    override fun getUserById(id: Int): Flow<UserWithDetails> =
        database.userDao().getUser(id = id).map { userEntity -> userEntity.toUserWithDetails() }
}