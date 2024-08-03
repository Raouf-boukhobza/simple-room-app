package com.raouf.simpleroomapp

import com.raouf.simpleroomapp.data.local.Contact

data class ContactState(
    val contacts : List<Contact> = emptyList(),
    val fistName : String = "",
    val lastName : String = "",
    val phoneNumber : String = "",
    val isAddingContact : Boolean = false,
    val sortType : Typesort = Typesort.FirstName
)
