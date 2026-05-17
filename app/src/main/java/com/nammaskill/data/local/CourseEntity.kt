package com.nammaskill.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: String,
    val name: String,
    val centerName: String,
    val duration: String,
    val category: String,
    val district: String
)
