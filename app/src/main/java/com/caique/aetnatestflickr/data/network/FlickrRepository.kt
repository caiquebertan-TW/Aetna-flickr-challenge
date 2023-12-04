package com.caique.aetnatestflickr.data.network

import com.caique.aetnatestflickr.data.model.PhotoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrRepository(private val flickrApi: FlickrApi) {
    suspend fun searchPhotos(query: String): List<PhotoItem> {
        return withContext(Dispatchers.IO) {
            flickrApi.searchPhotos(query).items
        }
    }
}
