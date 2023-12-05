@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalLayoutApi::class)

package com.caique.aetnatestflickr.feature.list.presentation

import android.content.res.Configuration
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.caique.aetnatestflickr.R
import com.caique.aetnatestflickr.data.model.Media
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.ui.design.AppTheme

@Composable
fun DetailScreen(photoItem: PhotoItem) {
    val configuration = LocalConfiguration.current

    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeDetail(photo = photoItem)
    } else {
        PortraitDetail(photo = photoItem)
    }
}

@Composable
fun PortraitDetail(photo: PhotoItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Fullscreen image
        GlideImage(
            model = photo.media.m,
            contentDescription = null,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            loading = placeholder(R.drawable.ic_launcher_foreground),
            failure = placeholder(R.drawable.ic_launcher_background)
        )

        // Photo details
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = photo.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Column(Modifier.semantics(mergeDescendants = true){}) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = "Description",
                    textAlign = TextAlign.Center
                )
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    factory = {
                        TextView(it).apply {
                            autoLinkMask = Linkify.WEB_URLS
                            linksClickable = true
                        }
                    },
                    update = { it.text = HtmlCompat.fromHtml(photo.description, 0) }
                )
            }

            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "Width: ${photo.media.width}"
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Height: ${photo.media.height}",
                textAlign = TextAlign.Center
            )
            Column(Modifier.semantics(mergeDescendants = true){}) {

                Text(text = "Author")

                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = photo.author
                )
            }

            Column(Modifier.semantics(mergeDescendants = true){}) {

                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Tags"
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    photo.getTags().forEach {
                        SuggestionChip(
                            onClick = {},
                            label = { Text(text = it) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LandscapeDetail(photo: PhotoItem) {
    // Fullscreen image without details
    GlideImage(
        model = photo.media.m,
        contentDescription = photo.title,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Fit,
        loading = placeholder(R.drawable.ic_launcher_foreground),
        failure = placeholder(R.drawable.ic_launcher_background)
    )
}

@Preview(showBackground = true)
@Composable
fun PhotoDetailScreenPreview() {
    AppTheme {
        DetailScreen(
            PhotoItem(
                title = "Title",
                author = "Caique Bertan",
                description = "<p>Lorem ipsum blablabla ".repeat(5),
                media = Media("https://google.com", 100, 100),
                tags ="teste 1 2 3 testando bigtexttagtoseehowitgoes"
            ))
    }
}