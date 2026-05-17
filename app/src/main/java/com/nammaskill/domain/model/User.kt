package com.nammaskill.domain.model

data class User(
    val uid: String = "",
    val name: String = "",
    val age: Int = 0,
    val gender: String = "",
    val district: String = "",
    val qualification: String = "",
    val interests: List<String> = emptyList(),
    val phoneNumber: String = "",
    val email: String = ""
)
