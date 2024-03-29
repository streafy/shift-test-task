package com.streafy.shifttesttask.navigation.destinations

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.streafy.shifttesttask.presentation.userdetails.UserDetailsScreen

private const val BASE_ROUTE = "details"
private const val ID_KEY = "id"

fun NavGraphBuilder.details(
    onBackClick: () -> Unit,
    onPhoneNumberClick: (phoneNumber: String, Context) -> Unit,
    onAddressClick: (address: String, Context) -> Unit,
    onEmailClick: (email: String, Context) -> Unit,
) {
    composable(
        route = "$BASE_ROUTE/{$ID_KEY}",
        arguments = listOf(
            navArgument(name = ID_KEY) {
                type = NavType.IntType
                nullable = false
            }
        )
    ) { navBackStackEntry ->
        val arguments = requireNotNull(navBackStackEntry.arguments)
        val id = arguments.getInt(ID_KEY)

        val context = LocalContext.current
        UserDetailsScreen(
            id = id,
            onBackClick = onBackClick,
            onPhoneNumberClick = { phoneNumber -> onPhoneNumberClick(phoneNumber, context) },
            onAddressClick = { address -> onAddressClick(address, context) },
            onEmailClick = { email -> onEmailClick(email, context) }
        )
    }
}

fun NavController.navigateToDetails(id: Int) {
    navigate("$BASE_ROUTE/$id")
}