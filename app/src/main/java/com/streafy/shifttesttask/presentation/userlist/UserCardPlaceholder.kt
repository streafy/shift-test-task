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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.streafy.shifttesttask.R

@Composable
fun UserCardPlaceholder(
    cardWidthFraction: Float = 1f
) {
    ElevatedCard(
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
            UserPhotoPlaceholder()
            UserInfoPlaceholder()
        }
    }
}

@Composable
private fun UserPhotoPlaceholder() {
    Box(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.card_height))
            .padding(dimensionResource(id = R.dimen.card_padding))
            .clip(CircleShape)
            .background(Color.Gray)
    )
}

@Composable
private fun UserInfoPlaceholder() {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.card_placeholder_text_spacing)
        )
    ) {
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.card_placeholder_text_width_medium))
                .height(dimensionResource(id = R.dimen.card_placeholder_text_height))
                .background(Color.Gray)
        )
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.card_placeholder_text_width_small))
                .height(dimensionResource(id = R.dimen.card_placeholder_text_height))
                .background(Color.Gray)
        )
        Box(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.card_placeholder_text_width_large))
                .height(dimensionResource(id = R.dimen.card_placeholder_text_height))
                .background(Color.Gray)
        )
    }
}


@Preview
@Composable
fun UserCardPlaceholderPreview() {
    UserCardPlaceholder()
}