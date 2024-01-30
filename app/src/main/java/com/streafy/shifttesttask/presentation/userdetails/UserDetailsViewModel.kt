package com.streafy.shifttesttask.presentation.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streafy.shifttesttask.domain.usecase.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UserDetailsUiState>(UserDetailsUiState.Initial)
    val state = _state.asStateFlow()

    fun loadUser(id: Int) {
        viewModelScope.launch {
            _state.value = UserDetailsUiState.Loading
            try {
                getUserByIdUseCase(id).collect { user ->
                    _state.value = UserDetailsUiState.Content(user)
                }
            } catch (e: Exception) {
                _state.value = UserDetailsUiState.Error(e.message)
            }
        }
    }
}