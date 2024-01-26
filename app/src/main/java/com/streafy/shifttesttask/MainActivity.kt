package com.streafy.shifttesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.streafy.shifttesttask.domain.entity.User
import com.streafy.shifttesttask.presentation.userlist.UserListScreen
import com.streafy.shifttesttask.ui.theme.ShifttesttaskTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mockData = List(10) { index ->
            User(
                firstName = "Test",
                lastName = "User",
                photoUri = "https://randomuser.me/api/portraits/women/$index.jpg",
                address = "Test address long long long long",
                phoneNumber = "098-66732533"
            )
        }

        setContent {
            ShifttesttaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserListScreen(users = mockData)
                }
            }
        }
    }
}