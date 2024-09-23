package com.cat.school.local.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cat.school.local.feature.task.api.TaskDao
import com.cat.school.local.feature.task.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
    ], version = 1
)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}