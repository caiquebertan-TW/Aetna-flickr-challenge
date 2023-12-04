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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caique.aetnatestflickr.R
import com.caique.aetnatestflickr.data.model.Media
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.ui.design.AppTheme
import org.koin.androidx.compose.getViewModel

val sampleItem = PhotoItem(
    id = "sample_id",
    title = "Sample Photo Title",
    media = Media("https://example.com/sample_photo.jpg", 100, 150)
)

@Composable
fun DetailScreen(photo: PhotoItem) {
    val configuration = LocalConfiguration.current

    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeDetail(photo = photo)
    } else {
        PortraitDetail(photo = photo)
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
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Description: Add photo description here")
            Text(text = "Width: ${photo.media.width}")
            Text(text = "Height: ${photo.media.height}")
            Text(text = "Author: Sample Author")
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
        DetailScreen(sampleItem)
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
        DetailScreen(sampleItem)
    }
}