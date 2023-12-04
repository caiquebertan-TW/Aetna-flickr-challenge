package com.caique.aetnatestflickr.feature.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.feature.list.domain.GetPhotosUseCase
import com.caique.aetnatestflickr.feature.list.domain.GetRecentSearchesUseCase
import com.caique.aetnatestflickr.feature.list.domain.AddRecentSearchUseCase
import com.caique.aetnatestflickr.util.ResultState
import com.caique.aetnatestflickr.util.WhileUiSubscribed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val getRecentSearchesUseCase: GetRecentSearchesUseCase,
    private val addRecentSearchUseCase: AddRecentSearchUseCase
) : ViewModel() {

    private val searchText = MutableStateFlow("")
    private val photos = MutableStateFlow<List<PhotoItem>>(emptyList())
    private val searches = MutableStateFlow<List<String>>(emptyList())
    private val loading = MutableStateFlow(false)

    val uiData = combine(searchText, photos, searches, loading) { text, photos, searches, loading ->
        ListUiData(
            searchText = text,
            photos = photos,
            searches = searches,
            isLoading = loading
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = ListUiData(),
        started = WhileUiSubscribed
    )

    fun search(text: String) = viewModelScope.launch {
        loading.emit(true)
        when(val resultPhotos = getPhotosUseCase(text)) {
            is ResultState.Success -> {
                searchText.emit(text)
                photos.emit(resultPhotos.data)
                loading.emit(false)
            }
            is ResultState.Error -> {
                photos.emit(emptyList())
                loading.emit(false)
            }
            else -> {
                loading.emit(true)
            }
        }
    }

    init {
        viewModelScope.launch {
            searches.emit(getRecentSearchesUseCase().toList())

            searchText.collectLatest {
                if(it.isNotBlank())
                    searches.emit(addRecentSearchUseCase(it))
            }

        }
    }
}

data class ListUiData(
    val searchText: String = "",
    val photos: List<PhotoItem> = emptyList(),
    val searches: List<String> = emptyList(),
    val isLoading: Boolean = false
)