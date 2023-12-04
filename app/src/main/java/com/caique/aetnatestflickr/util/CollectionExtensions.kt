package com.caique.aetnatestflickr.util

import android.text.TextUtils
import java.util.Collections

fun <T> Collection<T>.join(delimiter: String): String = TextUtils.join(delimiter, this)