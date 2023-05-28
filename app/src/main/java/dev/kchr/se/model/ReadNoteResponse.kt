package dev.kchr.se.model

import com.google.gson.annotations.SerializedName


class ReadNoteResponse (
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<Data>,
)