@file:OptIn(ExperimentalGlideComposeApi::class)

package com.caique.aetnatestflickr.feature.detail.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.caique.aetnatestflickr.R
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.feature.list.presentation.ListViewModel
import com.caique.aetnatestflickr.ui.design.AppTheme
import org.koin.androidx.compose.getViewModel

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
                .height(200.dp)
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

            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Description: ${photo.description}"
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Width: ${photo.media.width}"
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Height: ${photo.media.height}"
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Author: ${photo.author}"
            )
        }
    }
}

@Composable
fun LandscapeDetail(photo: PhotoItem) {
    // Fullscreen image without details
    GlideImage(
        model = photo.media.m,
        contentDescription = null,
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
//        DetailScreen()
    }
}