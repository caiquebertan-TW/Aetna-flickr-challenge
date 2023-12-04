package com.caique.aetnatestflickr.feature.list.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caique.aetnatestflickr.data.network.FlickrRepository
import com.caique.aetnatestflickr.feature.list.domain.GetRecentSearchQueriesUseCase
import com.caique.aetnatestflickr.feature.list.domain.RecentSearchRepository
import com.caique.aetnatestflickr.feature.list.model.SearchResultUiState
import com.caique.aetnatestflickr.feature.list.domain.GetSearchContentsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    private val flickrRepository: FlickrRepository,
) : ViewModel() {

}