package com.nammaskill.domain.model

data class Course(
    val id: String = "",
    val name: String = "",
    val centerName: String = "",
    val duration: String = "",
    val startDate: String = "",
    val availableSeats: Int = 0,
    val eligibility: String = "",
    val placementSupport: Boolean = false,
    val category: String = "",
    val district: String = "",
    val isFree: Boolean = true,
    val description: String = ""
)
