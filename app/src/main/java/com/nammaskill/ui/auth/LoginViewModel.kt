package com.nammaskill.ui.auth

import androidx.lifecycle.ViewModel
import com.nammaskill.domain.model.User
import com.nammaskill.domain.model.UserRole
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _userProfile = MutableStateFlow<User?>(null)
    val userProfile: StateFlow<User?> = _userProfile.asStateFlow()

    fun login(name: String, role: UserRole) {
        _userProfile.value = User(
            uid = "mock_uid",
            name = name,
            role = role,
            district = "Mandya"
        )
    }

    fun logout() {
        _userProfile.value = null
    }
}
