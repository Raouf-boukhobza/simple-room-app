package com.raouf.simpleroomapp

import com.raouf.simpleroomapp.data.local.Contact

data class ContactState(
    val Contacts : List<Contact> = emptyList(),
    val fistName : String = "",
    val lastName : String = "",
    val phoneNumber : String = "",
    val isaddingConact : Boolean = false,
    val sortType : Typesort = Typesort.FirstName
)
