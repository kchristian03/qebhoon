package dev.kchr.se.model

data class ApiResponse<Data>(
    val status: Boolean,
    val message: String,
    val data: Data)
