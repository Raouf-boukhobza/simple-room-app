package com.raouf.simpleroomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.raouf.simpleroomapp.ui.screen.HomeScreen
import com.raouf.simpleroomapp.ui.theme.SimpleRoomAppTheme
import com.raouf.simpleroomapp.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SimpleRoomAppTheme {
              val state = viewModel.state.collectAsState()
              HomeScreen(state = state ,viewModel::onEvent )

            }
        }
    }
}
