package dev.kchr.se.model

import java.sql.Time

data class Data(
    val ID: Int,
    val CreatedAt: String,
    val UpdatedAt: String,
    val DeletedAt: Any,
    val Title: String,
    val Description: String,
    val Due: String,
)