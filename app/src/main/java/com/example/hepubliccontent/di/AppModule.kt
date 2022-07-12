package com.example.hepubliccontent.di

import android.app.Application
import com.example.hepubliccontent.data.api.ApiService
import com.example.hepubliccontent.data.repository.PublicRepositoryImpl
import com.example.hepubliccontent.data.repository.dataSource.PublicDataSource
import com.example.hepubliccontent.data.repository.dataSourceImpl.PublicDataSourceImpl
import com.example.hepubliccontent.domain.repository.PublicRepository
import com.example.hepubliccontent.domain.usecase.GetPublicContentAppStyleUseCase
import com.example.hepubliccontent.domain.usecase.GetPublicContentListUseCase
import com.example.hepubliccontent.presentation.adapter.PublicContentAdapter
import com.example.hepubliccontent.presentation.viewmodel.PublicContentViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(" https://apiv2.demo-hubengage.com/")
            .build()
    }

    // Retrofit
    @Provides
    @Singleton
    fun provideGitHubAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    // Remote data
    @Provides
    @Singleton
    fun provideDataSource(apiService: ApiService): PublicDataSource {
        return PublicDataSourceImpl(apiService)
    }

    // Repository
    @Provides
    @Singleton
    fun provideGitHubRepository(
        publicDataSource: PublicDataSource
    ): PublicRepository {
        return PublicRepositoryImpl(publicDataSource)
    }

    // Use-cases
    @Provides
    @Singleton
    fun provideGetPublicContentListUseCase(
        publicRepository: PublicRepository,
    ): GetPublicContentListUseCase {
        return GetPublicContentListUseCase(publicRepository)
    }

    // Use-cases
    @Provides
    @Singleton
    fun provideGetPublicContentAppStyleUseCase(
        publicRepository: PublicRepository,
    ): GetPublicContentAppStyleUseCase {
        return GetPublicContentAppStyleUseCase(publicRepository)
    }

    // Adapter
    @Singleton
    @Provides
    fun providePublicContentAdapter(): PublicContentAdapter {
        return PublicContentAdapter()
    }

    // ViewModelFactory
    @Singleton
    @Provides
    fun providePublicContentViewModelFactory(
        getPublicContentListUseCase: GetPublicContentListUseCase,
        getPublicContentAppStyleUseCase: GetPublicContentAppStyleUseCase,
        app: Application
    ): PublicContentViewModelFactory {
        return PublicContentViewModelFactory(
            getPublicContentListUseCase,
            getPublicContentAppStyleUseCase,
            app
        )
    }
}