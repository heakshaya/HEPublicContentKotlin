package com.example.hepubliccontent.data.model.ApiStyle


import com.google.gson.annotations.SerializedName

data class HubLanguage(
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isDefault")
    val isDefault: Boolean,
    @SerializedName("name")
    val name: String
)