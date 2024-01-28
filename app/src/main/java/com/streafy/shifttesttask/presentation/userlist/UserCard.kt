package com.streafy.shifttesttask.presentation.userlist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.streafy.shifttesttask.domain.entity.User

@Composable
fun UserCard(
    user: User,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserPhoto(photoUri = user.photoUri)
            UserInfo(
                firstName = user.firstName,
                lastName = user.lastName,
                phoneNumber = user.phoneNumber,
                address = user.address
            )
        }
    }
}

@Composable
fun RowScope.UserPhoto(photoUri: String) {
    AsyncImage(
        model = photoUri,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .weight(2f)
            .fillMaxSize()
            .padding(15.dp)
            .clip(CircleShape)
    )

}

@Composable
private fun RowScope.UserInfo(
    firstName: String,
    lastName: String,
    phoneNumber: String,
    address: String
) {
    Column(
        Modifier
            .weight(5f)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = firstName, fontSize = 20.sp)
            Text(text = lastName, fontSize = 20.sp)
        }
        Text(text = phoneNumber)
        Text(text = address)
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun UserCardPreview() {
    val mockUser = User(
        firstName = "User",
        lastName = "lastName",
        photoUri = "https://randomuser.me/api/portraits/med/women/1.jpg",
        address = "test address",
        phoneNumber = "098-66732533",
        id = 1
    )
    UserCard(
        user = mockUser, modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}