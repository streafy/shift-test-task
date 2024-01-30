package com.streafy.shifttesttask.domain.usecase

import com.streafy.shifttesttask.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(id: Int) = repository.getUserById(id)
}