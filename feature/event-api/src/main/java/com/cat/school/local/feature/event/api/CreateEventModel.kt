package com.cat.school.local.feature.event.api

data class CreateEventModel(
    val name: String,
    val place: String? = null,
)