package com.raouf.simpleroomapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject


@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDB : RoomDatabase() {

    abstract fun  dao() : ContectsDao
}