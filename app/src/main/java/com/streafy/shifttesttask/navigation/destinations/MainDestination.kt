package com.streafy.shifttesttask.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.streafy.shifttesttask.domain.entity.User
import com.streafy.shifttesttask.presentation.userlist.UserListScreen

const val MAIN_SCREEN_ROUTE = "main"

fun NavGraphBuilder.main(
    onUserClick: (user: User) -> Unit
) {
    composable(route = MAIN_SCREEN_ROUTE) {
        UserListScreen(
            onUserClick = onUserClick
        )
    }
}