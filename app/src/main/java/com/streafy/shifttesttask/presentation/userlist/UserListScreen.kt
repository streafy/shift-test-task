package com.streafy.shifttesttask.presentation.userlist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = viewModel()
) {
    val users = viewModel.state.collectAsLazyPagingItems()

    LazyColumn(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users.itemCount) { index ->
            val user = users[index]
            user?.let { UserCard(user = it, modifier = Modifier.fillMaxWidth()) }
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun UserListScreenPreview() {
    UserListScreen()
}