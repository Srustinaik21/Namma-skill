package com.nammaskill.domain.repository

import com.nammaskill.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>
    suspend fun signInWithEmail(email: String, password: String): Result<Unit>
    suspend fun signUpWithEmail(email: String, password: String, user: User): Result<Unit>
    suspend fun signOut()
}
