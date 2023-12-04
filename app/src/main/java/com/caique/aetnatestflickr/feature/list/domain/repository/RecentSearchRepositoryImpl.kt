package com.caique.aetnatestflickr.feature.list.domain.repository

import android.content.SharedPreferences
import com.caique.aetnatestflickr.data.domain.RecentSearchRepository

class RecentSearchRepositoryImpl(
    private val preferences: SharedPreferences
): RecentSearchRepository {

    override suspend fun getRecentSearchQueries(): List<String> {
        return (preferences
            .getString("searches", null)
            ?.split(";")
                ?: emptyList()).asReversed()
    }

//    override suspend fun insertOrReplaceRecentSearch(searchQuery: String) {
//        if(searchQuery.isNotBlank()) {
//            val list = getRecentSearchQueries()
//            if (!list.contains(searchQuery)) {
//                preferences
//                    .edit()
//                    .putString("searches",
//                        list
//                            .toMutableList()
//                            .apply { add(lastIndex+1, searchQuery) }
//                            .join(";")
//                    )
//                    .apply()
//            }
//        }
//    }
//
//    override suspend fun removeLastSearch(): List<String> {
//        val listRemoved = getRecentSearchQueries()
//            .toMutableList()
//            .apply { removeLast() }
//            .join(";")
//
//        preferences
//            .edit()
//            .putString("searches", listRemoved)
//            .apply()
//        return listRemoved.split(";")
//    }

    override suspend fun updateRecentSearchs(searches: List<String>) {
        preferences
            .edit()
            .putString("searches", searches.joinToString(";"))
            .apply()
    }
}

