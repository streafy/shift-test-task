package com.streafy.shifttesttask.domain.usecase

import com.streafy.shifttesttask.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke() = repository.getUsers()
}