package com.example.hepubliccontent.data.api

import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.model.ApiStyle.APIStyle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "Accept: application/json"
    )
    @GET("pub/content/posts")
    suspend fun getPublicContentList(
        @Query("offset") page: Int,
        @Header("tenant") tenant: String,
        @Header("Accept-Language") languageCode: String
    ): Response<APIResponse>

    @Headers(
        "Accept: application/json"
    )
    @GET("pub/hub")
    suspend fun getPublicStyle(
        @Header("tenant") tenant: String,
        @Header("Accept-Language") languageCode: String
    ): Response<APIStyle>

}