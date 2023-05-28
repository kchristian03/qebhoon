package dev.kchr.se.model

import com.google.gson.annotations.SerializedName

data class EditNoteRequest(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("due") val due: String?,
)