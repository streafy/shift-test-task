package com.streafy.shifttesttask.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.streafy.shifttesttask.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val state = getUsersUseCase().cachedIn(viewModelScope)
}