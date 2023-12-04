package com.caique.aetnatestflickr.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class FlickrResponse(
    val items: List<PhotoItem>
)

@Parcelize
class PhotoItem(
    val title: String,
    val media: Media,
    val description: String,
    val author: String,
    val tags: String
): Parcelable {
    fun getTags() = tags.split(" ")
}

@Parcelize
data class Media(
    val m: String,
    val width: Int,
    val height: Int
): Parcelable
