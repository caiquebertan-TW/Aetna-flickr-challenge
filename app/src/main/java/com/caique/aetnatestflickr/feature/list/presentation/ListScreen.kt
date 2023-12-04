@file:OptIn(ExperimentalGlideComposeApi::class)

package com.caique.aetnatestflickr.feature.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.caique.aetnatestflickr.R
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.ui.components.SearchToolbar
import com.caique.aetnatestflickr.ui.design.AppTheme
import com.caique.aetnatestflickr.ui.navigateToDetail
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoList(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val viewModel: ListViewModel = getViewModel()
    val searchText = viewModel.searchText.collectAsState()
    val searches = viewModel.searches.collectAsState()
    val photos = viewModel.photos.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchToolbar(
            searchQuery = searchText.value,
            onSearchTriggered = {
                viewModel.search(it)
            },
            suggestions = searches.value
        )
        if(searchText.value.isNotBlank()) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Adaptive(180.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(photos.value) { photo ->
                    FlickrItem(
                        navController = navController,
                        photo = photo
                    )
                }
            }
        }
    }
}

@Composable
fun FlickrItem(photo: PhotoItem,
              navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigateToDetail(photo)
            }
    ) {
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

        Text(
            text = photo.title,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()

        )
    }
}

@Composable
@Preview
fun ListPreview() {
    AppTheme {
        PhotoList(
            navController = rememberNavController()
        )
    }
}

