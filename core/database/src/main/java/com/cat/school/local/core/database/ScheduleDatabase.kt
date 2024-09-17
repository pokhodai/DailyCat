package com.cat.school.local.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cat.school.local.core.database.api.AssignmentsDao
import com.cat.school.local.core.database.api.TaskDao
import com.cat.school.local.core.database.entities.AssignmentEntity
import com.cat.school.local.core.database.entities.TaskEntity

@Database(
    entities = [
        AssignmentEntity::class,
        TaskEntity::class,
    ], version = 1
)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun assignmentsDao(): AssignmentsDao
}