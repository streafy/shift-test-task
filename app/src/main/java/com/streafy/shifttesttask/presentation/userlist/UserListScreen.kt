package com.streafy.shifttesttask.presentation.userlist

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.streafy.shifttesttask.domain.entity.User

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = viewModel()
) {
    val users = viewModel.state.collectAsLazyPagingItems()

    val context = LocalContext.current
    LaunchedEffect(key1 = users.loadState) {
        if (users.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: ${(users.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (users.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            UserList(users)
        }
    }
}

@Composable
private fun UserList(users: LazyPagingItems<User>) {
    LazyColumn(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(users.itemCount, key = { index -> users[index]?.id ?: -1 }) { index ->
            val user = users[index]
            if (user != null) {
                UserCard(user = user, modifier = Modifier.fillMaxWidth())
            } else {
                //TODO: add placeholder
            }
        }

        item {
            if (users.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
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