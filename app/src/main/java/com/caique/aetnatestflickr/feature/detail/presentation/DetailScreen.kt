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
import com.caique.aetnatestflickr.R
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.feature.list.presentation.ListViewModel
import com.caique.aetnatestflickr.ui.design.AppTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(viewModel: ListViewModel = getViewModel()) {
    val configuration = LocalConfiguration.current
//    val viewModel: ListViewModel = viewModel()

    val photoItem by viewModel.detailUiState.collectAsStateWithLifecycle()

    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    photoItem?.let { photoItem ->
        if (isLandscape) {
            LandscapeDetail(photo = photoItem)
        } else {
            PortraitDetail(photo = photoItem)
        }
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
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with actual image
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
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
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with actual image
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun PhotoDetailScreenPreview() {
    AppTheme {
        DetailScreen()
    }
}

@Preview(name = "5-inch Device Landscape",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 640, heightDp = 360
    )
@Composable
fun LandscapePhotoDetailPreview() {
    AppTheme {
        DetailScreen()
    }
}