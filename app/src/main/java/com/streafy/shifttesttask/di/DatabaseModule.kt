package com.streafy.shifttesttask.di

import android.content.Context
import androidx.room.Room
import com.streafy.shifttesttask.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "users.db"
        ).build()
}