package com.cat.school.local.core.database.module

import android.content.Context
import androidx.room.Room
import com.cat.school.local.core.database.ScheduleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "SCHEDULE_DATABASE"

    @Provides
    @Singleton
    fun provideScheduleDatabase(
        @ApplicationContext context: Context,
    ): ScheduleDatabase {
        return Room
            .databaseBuilder(
                context,
                ScheduleDatabase::class.java,
                DATABASE_NAME
            )
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(scheduleDatabase: ScheduleDatabase) = scheduleDatabase.taskDao()
}