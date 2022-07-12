package com.example.hepubliccontent.data.model.ApiData


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("aspectRatio")
    val aspectRatio: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("videoUrl")
    val videoUrl: String
)