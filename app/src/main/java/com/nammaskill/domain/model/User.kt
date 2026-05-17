package com.nammaskill.domain.model

enum class UserRole {
    TRAINEE, TRAINER, JOB_SEEKER, JOB_PROVIDER
}

data class User(
    val uid: String = "",
    val name: String = "",
    val role: UserRole = UserRole.TRAINEE,
    val age: Int = 0,
    val gender: String = "",
    val district: String = "",
    val qualification: String = "",
    val interests: List<String> = emptyList(),
    val phoneNumber: String = "",
    val email: String = ""
)
