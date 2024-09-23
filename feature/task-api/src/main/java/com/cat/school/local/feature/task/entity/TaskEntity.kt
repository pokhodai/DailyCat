package com.cat.school.local.feature.task.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TaskEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: Int? = null
)