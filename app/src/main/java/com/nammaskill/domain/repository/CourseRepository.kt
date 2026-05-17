package com.nammaskill.domain.repository

import com.nammaskill.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getCourses(): Flow<List<Course>>
    suspend fun getCourseById(id: String): Course?
    suspend fun applyForCourse(userId: String, courseId: String): Result<Unit>
}
