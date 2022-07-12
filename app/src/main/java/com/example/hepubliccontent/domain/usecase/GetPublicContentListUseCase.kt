package com.example.hepubliccontent.domain.usecase

import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.util.Resource
import com.example.hepubliccontent.domain.repository.PublicRepository

class GetPublicContentListUseCase(private val repository: PublicRepository) {
    suspend fun execute(page: Int): Resource<APIResponse> {
        return repository.getPublicContentList(page)
    }
}