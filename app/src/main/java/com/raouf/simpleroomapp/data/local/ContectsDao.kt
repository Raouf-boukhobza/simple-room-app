package com.raouf.simpleroomapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContectsDao{

    @Upsert
    suspend fun upsertcontact(contact: Contact)

    @Delete
    suspend fun deletecontact(contact: Contact)

    @Query("select * from contact order by firstName ASC")
    fun getcontactorderbyfirstname() : Flow<List<Contact>>

    @Query("select * from contact order by lastName ASC")
    fun getcontactorderbylastname() : Flow<List<Contact>>

    @Query("select * from contact order by phoneNumber ASC")
    fun getcontactorderbyphonenumber() : Flow<List<Contact>>
}