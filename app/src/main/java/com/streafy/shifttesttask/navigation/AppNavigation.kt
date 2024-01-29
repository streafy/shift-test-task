package com.streafy.shifttesttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.streafy.shifttesttask.navigation.destinations.MAIN_SCREEN_ROUTE
import com.streafy.shifttesttask.navigation.destinations.details
import com.streafy.shifttesttask.navigation.destinations.main
import com.streafy.shifttesttask.navigation.destinations.navigateToDetails

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN_ROUTE
    ) {
        main(
            onUserClick = { user ->
                navController.navigateToDetails(user.id)
            }
        )
        details(
            onBackClick = navController::popBackStack
        )
    }
}