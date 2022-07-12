package com.example.hepubliccontent.data.repository

import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.model.ApiStyle.APIStyle
import com.example.hepubliccontent.data.repository.dataSource.PublicDataSource
import com.example.hepubliccontent.data.util.Resource
import com.example.hepubliccontent.domain.repository.PublicRepository
import retrofit2.Response

class PublicRepositoryImpl(
    private val publicDataSource: PublicDataSource
) : PublicRepository {
    override suspend fun getPublicContentList(page: Int): Resource<APIResponse> {
        return responseToResource(
            publicDataSource.getPublicContentList(page)
        )
    }

    override suspend fun getPublicStyle(): Resource<APIStyle> {
        return responseToResourceStyle(
            publicDataSource.getPublicStyle()
        )
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
    private fun responseToResourceStyle(response: Response<APIStyle>): Resource<APIStyle> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}