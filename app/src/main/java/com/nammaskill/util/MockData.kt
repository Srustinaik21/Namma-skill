package com.nammaskill.util

import com.nammaskill.domain.model.Course
import com.nammaskill.domain.model.SkillCenter

object MockData {
    val courses = listOf(
        Course(
            id = "1",
            name = "Mobile Repairing",
            centerName = "Rural Tech Center, Mandya",
            duration = "3 Months",
            startDate = "2024-06-01",
            availableSeats = 15,
            category = "Mobile Repair",
            district = "Mandya"
        ),
        Course(
            id = "2",
            name = "Advanced Sewing & Tailoring",
            centerName = "Women Empowerment Hub, Hassan",
            duration = "6 Months",
            startDate = "2024-06-15",
            availableSeats = 20,
            category = "Sewing",
            district = "Hassan"
        ),
        Course(
            id = "3",
            name = "Basic Coding & Computer Literacy",
            centerName = "Digital Skill Lab, Mysore",
            duration = "4 Months",
            startDate = "2024-07-01",
            availableSeats = 10,
            category = "Coding",
            district = "Mysore"
        )
    )

    val centers = listOf(
        SkillCenter(
            id = "c1",
            name = "Government Tool Room & Training Center",
            address = "Industrial Area, Mysore",
            latitude = 12.3072,
            longitude = 76.6551,
            rating = 4.5f
        )
    )
}
