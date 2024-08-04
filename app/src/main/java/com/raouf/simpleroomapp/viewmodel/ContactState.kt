package com.raouf.simpleroomapp.viewmodel

import com.raouf.simpleroomapp.data.local.Sorttype
import com.raouf.simpleroomapp.data.local.Contact

data class ContactState(
    val contacts : List<Contact> = emptyList(),
    var fistName : String = "",
    var lastName : String = "",
    var phoneNumber : String = "",
    val isAddingContact : Boolean = false,
    var sortType : Sorttype = Sorttype.FirstName
)
