package com.caique.aetnatestflickr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.caique.aetnatestflickr.data.model.Media
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.feature.detail.presentation.DetailScreen
import com.caique.aetnatestflickr.feature.list.presentation.PhotoList
import com.caique.aetnatestflickr.ui.ARG_DETAIL_PHOTO
import com.caique.aetnatestflickr.ui.Screen
import com.caique.aetnatestflickr.util.fromJson
import com.google.gson.Gson

@Composable
fun AppNavHost(
  navController: NavHostController,
  modifier: Modifier
) {
  NavHost(
    navController = navController,
    startDestination = Screen.HomeList.route,
    modifier = modifier
  ) {
    composable(Screen.HomeList.route) {
      PhotoList(navController, modifier)
    }
    composable(
      route = Screen.Detail.route,
      arguments = listOf(navArgument(ARG_DETAIL_PHOTO) { type = NavType.ParcelableType(PhotoItem::class.java) })
    ) { backStackEntry ->
      val rawPhoto = backStackEntry.arguments?.getString("photoId")
      rawPhoto?.let { DetailScreen(photo = it.fromJson()) }
    }
  }
}