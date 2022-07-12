package com.example.hepubliccontent.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hepubliccontent.domain.usecase.GetPublicContentAppStyleUseCase
import com.example.hepubliccontent.domain.usecase.GetPublicContentListUseCase

class PublicContentViewModelFactory(
    private val getPublicContentListUseCase: GetPublicContentListUseCase,
    private val getPublicContentAppStyleUseCase: GetPublicContentAppStyleUseCase,
    private val app:Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PublicContentViewModel(
            getPublicContentListUseCase,
            getPublicContentAppStyleUseCase,
            app
        )  as T
    }
}