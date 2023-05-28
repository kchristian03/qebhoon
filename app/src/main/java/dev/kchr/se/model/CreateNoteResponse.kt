package dev.kchr.se.model

import com.google.gson.annotations.SerializedName

data class CreateNoteResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data?,
) {
    data class Data(
        @SerializedName("ID") val ID: Int,
        @SerializedName("CreatedAt") val CreatedAt: String,
        @SerializedName("UpdatedAt") val UpdatedAt: String,
        @SerializedName("DeletedAt") val DeletedAt: String?,
        @SerializedName("Title") val Title: String,
        @SerializedName("Description") val Description: String,
        @SerializedName("Due") val Due: Any,
    )
}