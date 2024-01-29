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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.streafy.shifttesttask.domain.entity.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel(),
    onUserClick: (user: User) -> Unit = {}
) {
    val users = viewModel.state.collectAsLazyPagingItems()

    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        users.refresh()
        state.endRefresh()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = users.loadState) {
        if (users.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error occurred, swipe down to retry",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(state.nestedScrollConnection)
    ) {
        if (users.loadState.refresh is LoadState.Loading || state.isRefreshing) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            UserList(
                users = users,
                onUserClick = onUserClick
            )
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = state,
            )
        }
    }
}

@Composable
private fun UserList(
    users: LazyPagingItems<User>,
    onUserClick: (user: User) -> Unit
) {
    LazyColumn(
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Users",
                modifier = Modifier.padding(top = 12.dp),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        items(users.itemCount) { index ->
            val user = users[index]
            if (user != null) {
                UserCard(
                    user = user,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onUserClick(user) }
                )
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