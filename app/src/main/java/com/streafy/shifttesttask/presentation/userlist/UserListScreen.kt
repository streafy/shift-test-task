package com.streafy.shifttesttask.presentation.userlist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.streafy.shifttesttask.domain.entity.User

@Composable
fun UserListScreen(
    users: List<User>
) {
    LazyColumn(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users) {
            UserCard(user = it, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun UserListScreenPreview() {
    val mockData = List(10) { index ->
        User(
            firstName = "Test",
            lastName = "User",
            photoUri = "https://randomuser.me/api/portraits/women/$index.jpg",
            address = "test address",
            phoneNumber = "098-66732533"
        )
    }
    UserListScreen(
        mockData
    )
}