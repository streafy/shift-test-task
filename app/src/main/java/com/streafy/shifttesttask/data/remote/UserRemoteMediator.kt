package com.streafy.shifttesttask.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.streafy.shifttesttask.data.local.UserDatabase
import com.streafy.shifttesttask.data.local.UserEntity
import com.streafy.shifttesttask.data.mappers.toUserEntityList
import com.streafy.shifttesttask.data.utils.Config

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val database: UserDatabase,
    private val api: UserApi
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val pageNumber = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        ((lastItem.id + 1) / state.config.pageSize) + 1
                    }
                }
            }

            val response = api.getUsers(
                page = pageNumber,
                results = Config.ITEMS_PER_PAGE
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userDao().clearAll()
                }
                val userEntityList = response.toUserEntityList()
                database.userDao().insertAll(userEntityList)
            }

            return MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}