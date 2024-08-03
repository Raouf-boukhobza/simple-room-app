package com.raouf.simpleroomapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raouf.simpleroomapp.data.local.Contact
import com.raouf.simpleroomapp.data.local.ContectsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ContactViewModel @Inject constructor(
   private val dao : ContectsDao
) : ViewModel() {

    private val _state = MutableStateFlow(ContactState())
    private val _sortType= MutableStateFlow(Sorttype.FirstName)


    private val _contacts : StateFlow<List<Contact>> = _sortType
        .flatMapLatest { sortType ->
        when(sortType){
            Sorttype.FirstName -> dao.getcontactorderbyfirstname()
            Sorttype.LastName -> dao.getcontactorderbylastname()
            Sorttype.PhoneNumber -> dao.getcontactorderbyphonenumber()
        }
    }.stateIn(viewModelScope , SharingStarted.WhileSubscribed() , emptyList())


    val state = combine(_state , _sortType , _contacts ){state , sortytpe , contact ->
        state.copy(
            sortType = sortytpe,
            contacts = contact
        )
    }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event : ContactEvents){
        when(event){
            is ContactEvents.DeleteContect -> {
                viewModelScope.launch {
                    dao.deletecontact(event.contect)
                }
            }

            is ContactEvents.addFirstname -> {
                _state.update {
                    it.copy(
                        fistName = event.firstName
                    )
                }
            }
            is ContactEvents.addLastname ->{
                _state.update {
                    it.copy(
                        lastName = event.lastName
                    )
                }
            }


            is ContactEvents.addNumber -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }


            ContactEvents.hidedialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }

            ContactEvents.saveConact -> {
                val firstname = state.value.fistName
                val lastname= state.value.lastName
                val phonenumber = state.value.phoneNumber

                val contact = Contact(
                    firstName = firstname,
                    lastName = lastname,
                    phoneNumber = phonenumber
                )
                viewModelScope.launch {
                    dao.upsertcontact(contact)
                }
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        fistName = "",
                        lastName = "",
                        phoneNumber = ""
                    )
                }
            }

            ContactEvents.showdialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }

            is ContactEvents.sortContact-> {
                _sortType.value = event.sortType
            }

        }
    }


}