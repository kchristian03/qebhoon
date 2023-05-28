package dev.kchr.se.model

data class DataNote(
    val ID: Int,
    val CreatedAt: String,
    val UpdatedAt: String,
    val DeletedAt: String?,
    val Title: String,
    val Description: String,
    val Due: String,
)