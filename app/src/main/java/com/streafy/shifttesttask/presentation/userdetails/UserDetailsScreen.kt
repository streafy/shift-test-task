package com.streafy.shifttesttask.presentation.userdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun UserDetailsScreen(
    id: Int,
    onBackClick: () -> Unit
) {
    Text(text = "Details of user with id = $id")
}