package com.cat.school.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cat.school.db.dao.AssignmentsDao
import com.cat.school.db.dao.TaskDao
import com.cat.school.db.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun assignmentsDao(): AssignmentsDao
}