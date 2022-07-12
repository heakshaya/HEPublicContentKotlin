package com.example.hepubliccontent.data.model.ApiData


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("attachments")
    val attachments: List<Attachment>,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("contentCategory")
    val contentCategory: ContentCategory,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Image,
    @SerializedName("media")
    val media: List<Media>,
    @SerializedName("publishedOn")
    val publishedOn: String,
    @SerializedName("shareText")
    val shareText: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("uniqueId")
    val uniqueId: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("video")
    val video: Video
)