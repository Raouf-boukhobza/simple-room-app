package com.raouf.simpleroomapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Contanct(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val firstName : String,
    val lastName : String,
    val phoneNumber : String
)
