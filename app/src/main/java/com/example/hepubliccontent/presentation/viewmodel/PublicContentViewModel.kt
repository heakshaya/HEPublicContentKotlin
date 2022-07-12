package com.example.hepubliccontent.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hepubliccontent.data.model.ApiData.APIResponse
import com.example.hepubliccontent.data.model.ApiStyle.APIStyle
import com.example.hepubliccontent.data.util.Resource
import com.example.hepubliccontent.domain.usecase.GetPublicContentAppStyleUseCase
import com.example.hepubliccontent.domain.usecase.GetPublicContentListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicContentViewModel @Inject constructor(
    private val getPublicContentListUseCase: GetPublicContentListUseCase,
    private val getPublicContentAppStyleUseCase: GetPublicContentAppStyleUseCase,
    private val app: Application
) : AndroidViewModel(app) {
    val contentListMutableLiveData: MutableLiveData<Resource<APIResponse>> =
        MutableLiveData()
    val contentAppStyleMutableLiveData: MutableLiveData<Resource<APIStyle>> =
        MutableLiveData()

    fun getContentList(page: Int) = viewModelScope.launch(IO) {
        contentListMutableLiveData.postValue(Resource.Loading())
        try {
            val result = getPublicContentListUseCase.execute(page)
            contentListMutableLiveData.postValue(result)
        } catch (e: Exception) {
            contentListMutableLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getContentAppStyle() = viewModelScope.launch(IO) {
        contentAppStyleMutableLiveData.postValue(Resource.Loading())
        try {
            val result = getPublicContentAppStyleUseCase.execute()
            contentAppStyleMutableLiveData.postValue(result)
        } catch (e: Exception) {
            contentAppStyleMutableLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }

}