package com.caique.aetnatestflickr.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class FlickrResponse(
    val items: List<PhotoItem>
)

@Parcelize
class PhotoItem(
    val id: String,
    val title: String,
    val media: Media
): Parcelable

@Parcelize
data class Media(
    val m: String,
    val width: Int,
    val height: Int
): Parcelable
