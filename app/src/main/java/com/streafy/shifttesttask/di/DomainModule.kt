package com.streafy.shifttesttask.di

import com.streafy.shifttesttask.domain.repository.UserRepository
import com.streafy.shifttesttask.domain.usecase.GetUserByIdUseCase
import com.streafy.shifttesttask.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetUserByIdUseCase(repository: UserRepository) = GetUserByIdUseCase(repository)

    @Provides
    fun provideGetUsersUseCase(repository: UserRepository) = GetUsersUseCase(repository)
}