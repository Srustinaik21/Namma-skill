package com.nammaskill.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nammaskill.data.repository.AuthRepositoryImpl
import com.nammaskill.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import android.content.Context
import androidx.room.Room
import com.nammaskill.data.local.AppDatabase
import com.nammaskill.data.local.CourseDao
import dagger.hilt.android.qualifiers.ApplicationContext

import com.nammaskill.data.repository.CourseRepositoryImpl
import com.nammaskill.domain.repository.CourseRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(auth, firestore)

    @Provides
    @Singleton
    fun provideCourseRepository(
        firestore: FirebaseFirestore,
        courseDao: CourseDao
    ): CourseRepository = CourseRepositoryImpl(firestore, courseDao)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "namma_skill_db"
        ).build()
    }

    @Provides
    fun provideCourseDao(db: AppDatabase): CourseDao = db.courseDao()
}
