package com.raouf.simpleroomapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raouf.simpleroomapp.data.local.Sorttype
import com.raouf.simpleroomapp.viewmodel.ContactEvents
import com.raouf.simpleroomapp.viewmodel.ContactState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: State<ContactState>,
    onevent: (ContactEvents) -> Unit
) {
     Scaffold(
        floatingActionButton = {
           FloatingActionButton(
              onClick = {
            onevent(ContactEvents.showdialog)
           }) {
              Icon(
                  imageVector = Icons.Default.Add,
                  contentDescription = "add contact"
              )
           }
       }



    ){ paddingValues ->
        LazyColumn(
          contentPadding = paddingValues,
          modifier = Modifier
              .fillMaxSize()
              .padding(8.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
          ){
             item {
                 Row (
                     modifier = Modifier
                         .fillMaxWidth()
                         .horizontalScroll(state = rememberScrollState()),
                     horizontalArrangement = Arrangement.spacedBy(12.dp)
                 ){
                     Sorttype.entries.forEach { sorttype ->
                         Row (modifier = Modifier.clickable{
                             onevent(ContactEvents.sortContact(sorttype))
                         },
                             verticalAlignment = Alignment.CenterVertically){
                            RadioButton(
                                selected = state.value.sortType == sorttype,
                                onClick = {}
                            )

                             Text(text = sorttype.name)
                         }
                     }

                 }
             }
              items(state.value.contacts){contact ->
                  Row (modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceBetween,
                      verticalAlignment = Alignment.CenterVertically){
                      Column {
                          Text(text = "${contact.firstName}  ${contact.lastName}" , fontSize = 20.sp)
                          Text(text = contact.phoneNumber , fontSize = 18.sp)
                      }
                      IconButton(onClick = {
                          onevent(ContactEvents.DeleteContect(contact))
                      }){
                          Icon(imageVector = Icons.Default.Delete,
                          contentDescription = "delete contact",
                          Modifier.size(30.dp)
                          )
                      }
                  }
              }
        }


         if (state.value.isAddingContact){
             AlertDialog(
                 onDismissRequest = { onevent(ContactEvents.hidedialog)}
             ){
                 Column( verticalArrangement = Arrangement.spacedBy(24.dp) ,
                     modifier =Modifier.background(color = Color.LightGray , shape = RoundedCornerShape(24.dp))
                         .padding(24.dp)){
                     TextField(
                         value = state.value.fistName,
                         onValueChange = {onevent(ContactEvents.addFirstname(it))},
                         label = { Text(text = "First Name")}
                     )
                     TextField(value = state.value.lastName,
                         onValueChange = {onevent(ContactEvents.addLastname(it))},
                         label = { Text(text = "Last Name")})
                     TextField(value = state.value.phoneNumber,
                         onValueChange = {onevent(ContactEvents.addNumber(it))},
                         label = { Text(text = "Phone Number")})
                     Button(onClick = { onevent(ContactEvents.saveConact) }) {
                         Text(text = "Save")
                     }
                 }
             }
         }
   }
}


@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {

}