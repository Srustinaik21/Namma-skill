package com.nammaskill.domain.model

data class SkillCenter(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val contactNumber: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val coursesAvailable: List<String> = emptyList(),
    val rating: Float = 0f
)
