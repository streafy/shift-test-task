package com.streafy.shifttesttask.presentation.userdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.streafy.shifttesttask.domain.entity.UserWithDetails

@Composable
fun UserDetailsScreen(
    id: Int,
    onBackClick: () -> Unit,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.loadUser(id)
    }

    when (val stateValue = state.value) {
        is UserDetailsUiState.Content ->
            Content(
                user = stateValue.user,
                onBackClick = onBackClick
            )
        is UserDetailsUiState.Error ->
            ErrorContent(
                errorMessage = stateValue.message,
                onRetry = { viewModel.loadUser(id) }
            )
        UserDetailsUiState.Initial -> Unit
        UserDetailsUiState.Loading -> LoadingContent()
    }
}

@Composable
private fun Content(
    user: UserWithDetails,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBackClick)
        val orientation = LocalConfiguration.current.orientation
        UserDetailsCard(user = user, orientation = orientation)
    }
}

@Composable
private fun TopBar(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                null
            )
        }
        Text(
            text = "Details",
            modifier = Modifier.padding(vertical = 12.dp),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMessage)
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}