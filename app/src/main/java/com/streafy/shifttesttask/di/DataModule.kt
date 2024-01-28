package com.streafy.shifttesttask.di

import androidx.paging.Pager
import com.streafy.shifttesttask.data.local.UserEntity
import com.streafy.shifttesttask.data.repository.UserRepositoryImpl
import com.streafy.shifttesttask.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(pager: Pager<Int, UserEntity>): UserRepository =
        UserRepositoryImpl(pager = pager)
}