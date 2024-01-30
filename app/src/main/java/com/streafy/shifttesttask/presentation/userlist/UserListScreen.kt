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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.streafy.shifttesttask.R
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
    val errorMessage = stringResource(id = R.string.error_message)
    LaunchedEffect(key1 = users.loadState) {
        if (users.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                errorMessage,
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
        Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.container_horizontal_padding))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_spacing)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = stringResource(id = R.string.main_screen_title),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.list_spacing)),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        items(users.itemCount) { index ->
            val user = users[index]

            val orientation = LocalConfiguration.current.orientation
            val cardWidthFraction =
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) 0.5f else 1f

            if (user != null) {
                UserCard(
                    user = user,
                    cardWidthFraction = cardWidthFraction,
                    onClick = { onUserClick(user) }
                )
            } else {
                UserCardPlaceholder(
                    cardWidthFraction = cardWidthFraction
                )
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