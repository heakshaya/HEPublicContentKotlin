package com.example.hepubliccontent.data.repository.dataSource

import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.model.ApiStyle.APIStyle
import retrofit2.Response

interface PublicDataSource {
    suspend fun getPublicContentList(page:Int): Response<APIResponse>
    suspend fun getPublicStyle(): Response<APIStyle>
}