package com.raouf.simpleroomapp.viewmodel

import com.raouf.simpleroomapp.data.local.Sorttype
import com.raouf.simpleroomapp.data.local.Contact


sealed interface ContactEvents {
    data object saveConact : ContactEvents
    data class addFirstname(val firstName : String) : ContactEvents
    data class addLastname(val lastName : String) : ContactEvents
    data class addNumber(val phoneNumber : String) : ContactEvents
    data object showdialog : ContactEvents
    data object hidedialog : ContactEvents
    data class sortContact (val sortType : Sorttype) : ContactEvents
    data class DeleteContect(val contact : Contact) : ContactEvents
}