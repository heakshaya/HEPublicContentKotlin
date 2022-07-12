package com.example.hepubliccontent.domain.usecase

import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.model.ApiStyle.APIStyle
import com.example.hepubliccontent.data.util.Resource
import com.example.hepubliccontent.domain.repository.PublicRepository

class GetPublicContentAppStyleUseCase (private val repository: PublicRepository) {
    suspend fun execute(): Resource<APIStyle> {
        return repository.getPublicStyle()
    }
}