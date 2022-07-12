package com.example.hepubliccontent.data.model.ApiStyle


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("appUi")
    val appUi: AppUi,
    @SerializedName("companyName")
    val companyName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("domain")
    val domain: String,
    @SerializedName("hubLanguages")
    val hubLanguages: List<HubLanguage>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("supportEmail")
    val supportEmail: String,
    @SerializedName("website")
    val website: String
)