package com.caique.aetnatestflickr.feature.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caique.aetnatestflickr.data.model.Media
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.data.network.FlickrRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

val recentSearchs = List(5) {
    "Search $it"
}

class ListViewModel(
    private val flickrRepository: FlickrRepository,
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    var searchText: StateFlow<String> = _searchText

    private val _photos = MutableStateFlow<List<PhotoItem>>(emptyList())
    var photos: StateFlow<List<PhotoItem>> = _photos

    private val _searches = MutableStateFlow(recentSearchs)
    var searches: StateFlow<List<String>> = _searches

    init {
        viewModelScope.launch {
            _searchText.collectLatest {
                if(it.isNotBlank()) {
                    //TODO saveSearch? or save only if gets results?
                }
            }
        }
    }
    fun search(text: String) = viewModelScope.launch {
        _searchText.emit(text)
        _photos.emit(flickrRepository.searchPhotos(text))
    }

}