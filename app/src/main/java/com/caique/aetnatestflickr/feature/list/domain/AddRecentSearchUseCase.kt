package com.caique.aetnatestflickr.feature.list.domain

import com.caique.aetnatestflickr.data.domain.RecentSearchRepository

class AddRecentSearchUseCase(
    private val repository: RecentSearchRepository,
) {
    suspend operator fun invoke(
        searchQuery: String,
    ): List<String> {
        if(searchQuery.isNotBlank()) {
            var searches = repository.getRecentSearchQueries()

            if (searches.size >= 5)
                searches = repository.removeLastSearch()

            repository.insertOrReplaceRecentSearch(searchQuery)

            return searches.toMutableList().apply { add(0, searchQuery) }
        }
        return emptyList()
    }
}