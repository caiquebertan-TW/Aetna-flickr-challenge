package com.caique.aetnatestflickr.feature.list.domain

import com.caique.aetnatestflickr.data.domain.FlickrRepository

class GetPhotosUseCase(
    private val repository: FlickrRepository,
) {

    suspend operator fun invoke(
        searchQuery: String,
    ) = repository.searchPhotos(searchQuery)
}