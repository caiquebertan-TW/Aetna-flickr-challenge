package com.caique.aetnatestflickr.di

import com.caique.aetnatestflickr.data.network.FlickrApi
import com.caique.aetnatestflickr.data.network.FlickrRepository
import com.caique.aetnatestflickr.feature.detail.presentation.DetailViewModel
import com.caique.aetnatestflickr.feature.list.domain.GetRecentSearchQueriesUseCase
import com.caique.aetnatestflickr.feature.list.domain.GetSearchContentsUseCase
import com.caique.aetnatestflickr.feature.list.presentation.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideRetrofit() }
    single { provideFlickrApi(get()) }
    single { FlickrRepository(get()) }

    viewModel { ListViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.flickr.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideFlickrApi(retrofit: Retrofit): FlickrApi {
    return retrofit.create(FlickrApi::class.java)
}
