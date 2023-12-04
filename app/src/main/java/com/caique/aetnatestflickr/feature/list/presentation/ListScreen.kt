@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalGlideComposeApi::class)

package com.caique.aetnatestflickr.feature.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.caique.aetnatestflickr.R
import com.caique.aetnatestflickr.data.model.Media
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.ui.Screen
import com.caique.aetnatestflickr.ui.components.SearchToolbar
import com.caique.aetnatestflickr.ui.navigateToDetail
import org.koin.androidx.compose.getViewModel

val fakePhotoItemList: List<PhotoItem> = List(40) { index ->
    PhotoItem(
        id = "id_$index",
        title = "Photo Title $index",
        media = Media("https://example.com/photo_$index.jpg", 100, 150)
    )
}

val recentSearchs = List(5) {
    "Search $it"
}


@Composable
fun PhotoList(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = getViewModel()
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchToolbar(
            onSearchTriggered = {
                searchText = it
            },
            suggestions = recentSearchs
        )
        if(searchText.isNotBlank()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(fakePhotoItemList) { photo ->
                        FlickrItem(
                            navController = navController,
                            photo = photo
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
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

