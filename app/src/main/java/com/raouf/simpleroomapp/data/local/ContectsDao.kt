package com.raouf.simpleroomapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContectsDao{
    @Upsert
    suspend fun upsertcontact(contanct: Contanct)

    @Delete
    suspend fun deletecontact(contanct: Contanct)

    @Query("select * from contanct order by firstName ASC")
    fun getcontactorderbyfirstname() : Flow<List<Contanct>>

    @Query("select * from contanct order by lastName ASC")
    fun getcontactorderbylastname() : Flow<List<Contanct>>

    @Query("select * from contanct order by phoneNumber ASC")
    fun getcontactorderbyphonenumber() : Flow<List<Contanct>>
}