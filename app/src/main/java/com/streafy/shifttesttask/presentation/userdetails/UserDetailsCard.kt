package com.streafy.shifttesttask.presentation.userdetails

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.streafy.shifttesttask.R
import com.streafy.shifttesttask.domain.entity.UserWithDetails

@Composable
fun UserDetailsCard(
    user: UserWithDetails,
    orientation: Int = Configuration.ORIENTATION_PORTRAIT,
    onPhoneNumberClick: (String) -> Unit,
    onAddressClick: (String) -> Unit,
    onEmailCLick: (String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            HorizontalContent(
                user = user,
                onPhoneNumberClick = onPhoneNumberClick,
                onAddressClick = onAddressClick,
                onEmailCLick = onEmailCLick
            )
        } else {
            VerticalContent(
                user = user,
                onPhoneNumberClick = onPhoneNumberClick,
                onAddressClick = onAddressClick,
                onEmailCLick = onEmailCLick
            )
        }
    }
}

@Composable
private fun VerticalContent(
    user: UserWithDetails,
    onPhoneNumberClick: (String) -> Unit,
    onAddressClick: (String) -> Unit,
    onEmailCLick: (String) -> Unit
) {
    AsyncImage(
        model = user.photoUri,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.loading_image),
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
    UserInfo(
        user = user,
        onPhoneNumberClick = onPhoneNumberClick,
        onAddressClick = onAddressClick,
        onEmailCLick = onEmailCLick
    )
}

@Composable
private fun HorizontalContent(
    user: UserWithDetails,
    onPhoneNumberClick: (String) -> Unit,
    onAddressClick: (String) -> Unit,
    onEmailCLick: (String) -> Unit
) {
    Row {
        AsyncImage(
            model = user.photoUri,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.loading_image),
            modifier = Modifier
                .fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        UserInfo(
            user = user,
            onPhoneNumberClick = onPhoneNumberClick,
            onAddressClick = onAddressClick,
            onEmailCLick = onEmailCLick
        )
    }
}

@Composable
private fun UserInfo(
    user: UserWithDetails,
    onPhoneNumberClick: (String) -> Unit,
    onAddressClick: (String) -> Unit,
    onEmailCLick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        InfoField(name = "Full name", content = "${user.firstName} ${user.lastName}")
        InfoField(name = "Gender", content = user.gender)
        InfoField(name = "Phone", content = user.phoneNumber, onClick = onPhoneNumberClick)
        InfoField(name = "Address", content = user.address, onClick = onAddressClick)
        InfoField(name = "Email", content = user.email, onClick = onEmailCLick)
        InfoField(name = "Date of birth", content = user.dateOfBirth)
        InfoField(name = "Registered", content = user.registered)
    }
}

@Composable
private fun InfoField(
    name: String,
    content: String,
    onClick: (String) -> Unit = {}
) {
    Column(modifier = Modifier.clickable { onClick(content) }) {
        Text(text = name, style = MaterialTheme.typography.titleSmall)
        Text(text = content, style = MaterialTheme.typography.bodyMedium)
    }
}