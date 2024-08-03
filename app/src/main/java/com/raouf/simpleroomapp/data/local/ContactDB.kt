package com.raouf.simpleroomapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Contanct::class],
    version = 1
)
abstract class ContactDB : RoomDatabase() {
    abstract val dao : ContectsDao



}