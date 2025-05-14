package com.example.recipebook.domain.usecase

import com.example.recipebook.domain.di.qualifies.IoDispatchers
import com.example.recipebook.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(phone: String, password: String): Boolean {
        return withContext(dispatcher) {
            userRepository.login(phone, password)
        }
    }
}
