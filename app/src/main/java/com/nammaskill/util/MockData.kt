package com.nammaskill.util

import com.nammaskill.domain.model.Course
import com.nammaskill.domain.model.SkillCenter

object MockData {
    val courses = listOf(
        Course(
            id = "1",
            name = "Mobile Repairing & Servicing",
            centerName = "Rural Tech Center, Mandya",
            duration = "3 Months",
            startDate = "2024-06-01",
            availableSeats = 15,
            category = "Mobile Repair",
            district = "Mandya",
            isFree = true
        ),
        Course(
            id = "2",
            name = "Advanced Sewing & Fashion Design",
            centerName = "Women Empowerment Hub, Hassan",
            duration = "6 Months",
            startDate = "2024-06-15",
            availableSeats = 20,
            category = "Sewing",
            district = "Hassan",
            isFree = true
        ),
        Course(
            id = "3",
            name = "Full Stack Web Development",
            centerName = "Digital Skill Lab, Mysore",
            duration = "4 Months",
            startDate = "2024-07-01",
            availableSeats = 10,
            category = "Coding",
            district = "Mysore",
            isFree = false
        ),
        Course(
            id = "4",
            name = "Electrician & House Wiring",
            centerName = "Govt ITI, Tumkur",
            duration = "1 Year",
            startDate = "2024-08-01",
            availableSeats = 40,
            category = "Electrician",
            district = "Tumkur",
            isFree = true
        ),
        Course(
            id = "5",
            name = "Modern Farming Techniques",
            centerName = "Krishi Vigyan Kendra, Raichur",
            duration = "2 Months",
            startDate = "2024-05-20",
            availableSeats = 50,
            category = "Agriculture",
            district = "Raichur",
            isFree = true
        ),
        Course(
            id = "6",
            name = "Hospitality & Hotel Management",
            centerName = "Skill Hub, Bangalore",
            duration = "6 Months",
            startDate = "2024-09-10",
            availableSeats = 25,
            category = "Hospitality",
            district = "Bangalore",
            isFree = false
        ),
        Course(
            id = "7",
            name = "Solar Panel Installation",
            centerName = "Green Energy Institute, Hubli",
            duration = "3 Months",
            startDate = "2024-07-15",
            availableSeats = 12,
            category = "Electrician",
            district = "Dharwad",
            isFree = true
        ),
        Course(
            id = "8",
            name = "Data Entry & Office Automation",
            centerName = "Community Center, Belagavi",
            duration = "3 Months",
            startDate = "2024-06-05",
            availableSeats = 30,
            category = "Coding",
            district = "Belagavi",
            isFree = true
        ),
        Course(
            id = "9",
            name = "Two-Wheeler Mechanic",
            centerName = "Auto Care Center, Shimoga",
            duration = "4 Months",
            startDate = "2024-06-25",
            availableSeats = 18,
            category = "Automobile",
            district = "Shimoga",
            isFree = true
        ),
        Course(
            id = "10",
            name = "Beautician & Wellness",
            centerName = "Srusti Skill Center, Davanagere",
            duration = "3 Months",
            startDate = "2024-08-15",
            availableSeats = 15,
            category = "Wellness",
            district = "Davanagere",
            isFree = false
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
