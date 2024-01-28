package com.streafy.shifttesttask.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.streafy.shifttesttask.data.local.UserDatabase
import com.streafy.shifttesttask.data.local.UserEntity
import com.streafy.shifttesttask.data.remote.UserApi
import com.streafy.shifttesttask.data.remote.UserRemoteMediator
import com.streafy.shifttesttask.data.utils.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
class PagingModule {

    @Provides
    @Singleton
    fun providePager(api: UserApi, database: UserDatabase): Pager<Int, UserEntity> =
        Pager(
            config = PagingConfig(
                pageSize = Config.ITEMS_PER_PAGE,
                initialLoadSize = Config.ITEMS_PER_PAGE
            ),
            remoteMediator = UserRemoteMediator(
                api = api,
                database = database
            ),
            pagingSourceFactory = { database.userDao().pagingSource() }
        )
}