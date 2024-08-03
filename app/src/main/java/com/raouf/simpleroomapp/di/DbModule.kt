package com.raouf.simpleroomapp.di

import android.content.Context
import androidx.room.Room
import com.raouf.simpleroomapp.data.local.ContactDB
import com.raouf.simpleroomapp.data.local.ContectsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun providedb(@ApplicationContext context : Context) : ContactDB{
        return Room.databaseBuilder(context , ContactDB::class.java , "Contact")
            .build()
    }

    @Provides
    fun providedao(conctactdb : ContactDB) : ContectsDao{
        return conctactdb.dao
    }

}