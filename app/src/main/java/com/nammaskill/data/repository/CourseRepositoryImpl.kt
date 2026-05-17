package com.nammaskill.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.nammaskill.data.local.CourseDao
import com.nammaskill.data.local.CourseEntity
import com.nammaskill.domain.model.Course
import com.nammaskill.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val courseDao: CourseDao
) : CourseRepository {

    override fun getCourses(): Flow<List<Course>> {
        // Return local DB flow, but we'll seed it with mock data if empty
        return courseDao.getAllCourses().map { entities ->
            if (entities.isEmpty()) {
                com.nammaskill.util.MockData.courses
            } else {
                entities.map { it.toDomain() }
            }
        }
    }

    suspend fun syncCourses() {
        try {
            val snapshot = firestore.collection("courses").get().await()
            val courses = snapshot.toObjects(Course::class.java)
            courseDao.insertCourses(courses.map { it.toEntity() })
        } catch (e: Exception) {
            // Log error
        }
    }

    override suspend fun getCourseById(id: String): Course? {
        return try {
            firestore.collection("courses").document(id).get().await().toObject(Course::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun applyForCourse(userId: String, courseId: String): Result<Unit> {
        return try {
            val application = hashMapOf(
                "userId" to userId,
                "courseId" to courseId,
                "timestamp" to System.currentTimeMillis(),
                "status" to "Pending"
            )
            firestore.collection("applications").add(application).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

// Extension functions for mapping
fun CourseEntity.toDomain() = Course(id = id, name = name, centerName = centerName, duration = duration, category = category, district = district)
fun Course.toEntity() = CourseEntity(id = id, name = name, centerName = centerName, duration = duration, category = category, district = district)
