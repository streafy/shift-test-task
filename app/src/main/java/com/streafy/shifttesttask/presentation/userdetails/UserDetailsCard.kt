package com.streafy.shifttesttask.presentation.userdetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.streafy.shifttesttask.domain.entity.UserWithDetails

@Composable
fun UserDetailsCard(
    user: UserWithDetails,
    orientation: Int = Configuration.ORIENTATION_PORTRAIT
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            HorizontalContent(user = user)
        } else {
            VerticalContent(user = user)
        }
    }
}

@Composable
private fun VerticalContent(
    user: UserWithDetails
) {
    AsyncImage(
        model = user.photoUri,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
    UserInfo(user)
}

@Composable
private fun HorizontalContent(
    user: UserWithDetails
) {
    Row {
        AsyncImage(
            model = user.photoUri,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        UserInfo(user)
    }
}

@Composable
private fun UserInfo(
    user: UserWithDetails,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        InfoField(name = "Full name", content = "${user.firstName} ${user.lastName}")
        InfoField(name = "Gender", content = user.gender)
        InfoField(name = "Phone", content = user.phoneNumber)
        InfoField(name = "Address", content = user.address)
        InfoField(name = "Date of birth", content = user.dateOfBirth)
        InfoField(name = "Registered", content = user.registered)
    }
}

@Composable
private fun InfoField(
    name: String,
    content: String
) {
    Column {
        Text(text = name, style = MaterialTheme.typography.titleSmall)
        Text(text = content, style = MaterialTheme.typography.bodyMedium)
    }
}