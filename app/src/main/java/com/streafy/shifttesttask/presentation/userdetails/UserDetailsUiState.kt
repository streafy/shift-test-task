package com.streafy.shifttesttask.presentation.userdetails

import com.streafy.shifttesttask.domain.entity.UserWithDetails

sealed interface UserDetailsUiState {

    data object Initial : UserDetailsUiState
    data object Loading : UserDetailsUiState
    data class Content(val user: UserWithDetails) : UserDetailsUiState
    data class Error(val message: String) : UserDetailsUiState
}