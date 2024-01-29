package com.streafy.shifttesttask.presentation.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserCardPlaceholder(
    cardWidthFraction: Float = 1f
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(cardWidthFraction)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserPhotoPlaceholder()
            UserInfoPlaceholder()
        }
    }
}

@Composable
private fun UserPhotoPlaceholder() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(15.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    )
}

@Composable
private fun UserInfoPlaceholder() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(220.dp)
                .height(20.dp)
                .background(Color.Gray)
        )
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(20.dp)
                .background(Color.Gray)
        )
        Box(
            modifier = Modifier
                .width(240.dp)
                .height(20.dp)
                .background(Color.Gray)
        )
    }
}


@Preview
@Composable
fun UserCardPlaceholderPreview() {
    UserCardPlaceholder()
}