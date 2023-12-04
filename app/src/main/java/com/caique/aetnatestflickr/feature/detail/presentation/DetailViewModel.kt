package com.caique.aetnatestflickr.feature.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.data.network.FlickrRepository
import com.caique.aetnatestflickr.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val flickrRepository: FlickrRepository
) : ViewModel() {
}
