package com.cat.school.local.core.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("task")
data class TaskEntity(
    @PrimaryKey val id: String = ""
)