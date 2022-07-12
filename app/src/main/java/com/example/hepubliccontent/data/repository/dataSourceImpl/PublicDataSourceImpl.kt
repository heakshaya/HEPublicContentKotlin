package com.example.hepubliccontent.data.repository.dataSourceImpl

import android.util.Log
import com.example.hepubliccontent.data.api.ApiService
import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.model.ApiStyle.APIStyle
import com.example.hepubliccontent.data.repository.dataSource.PublicDataSource
import retrofit2.Response

class PublicDataSourceImpl(
    private val apiService: ApiService
):PublicDataSource {
    private val tenant="client0"
    private val languageCode="en"
    override suspend fun getPublicContentList(page: Int): Response<APIResponse> {
        return apiService.getPublicContentList(page,tenant,languageCode)
    }

    override suspend fun getPublicStyle(): Response<APIStyle> {
        return apiService.getPublicStyle(tenant, languageCode)
    }
}