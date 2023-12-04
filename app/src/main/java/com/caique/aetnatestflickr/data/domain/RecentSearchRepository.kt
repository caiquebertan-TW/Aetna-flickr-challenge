package com.caique.aetnatestflickr.data.domain

import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {

    suspend fun getRecentSearchQueries(): List<String>

    suspend fun insertOrReplaceRecentSearch(searchQuery: String)

    suspend fun removeLastSearch(): List<String>
}


