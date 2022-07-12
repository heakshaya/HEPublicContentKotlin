package com.example.hepubliccontent.domain.repository

import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.model.ApiStyle.APIStyle
import com.example.hepubliccontent.data.util.Resource
import retrofit2.Response

interface PublicRepository {
    suspend fun getPublicContentList(page:Int): Resource<APIResponse>
    suspend fun getPublicStyle(): Resource<APIStyle>
}