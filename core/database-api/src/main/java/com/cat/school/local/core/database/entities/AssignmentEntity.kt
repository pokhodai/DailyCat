package com.cat.school.local.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("table_assignments")
class AssignmentEntity(
    @PrimaryKey val id: String = ""
)