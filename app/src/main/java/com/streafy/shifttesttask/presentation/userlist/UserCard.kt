package com.streafy.shifttesttask.presentation.userlist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.streafy.shifttesttask.R
import com.streafy.shifttesttask.domain.entity.User

@Composable
fun UserCard(
    user: User,
    cardWidthFraction: Float = 1f,
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(cardWidthFraction)
            .height(dimensionResource(id = R.dimen.card_height)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
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
private fun UserPhoto(photoUri: String) {
    AsyncImage(
        model = photoUri,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.loading_image),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.card_height))
            .padding(dimensionResource(id = R.dimen.card_padding))
            .clip(CircleShape)
    )

}

@Composable
private fun UserInfo(
    firstName: String,
    lastName: String,
    phoneNumber: String,
    address: String
) {
    Column(
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.card_padding)),
        verticalArrangement = Arrangement.Center,
    ) {

        UserInfoField(
            text = "$firstName $lastName",
            style = MaterialTheme.typography.titleLarge
        )
        UserInfoField(text = phoneNumber)
        UserInfoField(text = address)
    }
}

@Composable
private fun UserInfoField(
    text: String,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        style = style
    )
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
        user = mockUser
    )
}