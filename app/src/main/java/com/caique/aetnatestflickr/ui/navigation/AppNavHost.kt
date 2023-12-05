package com.caique.aetnatestflickr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.feature.detail.presentation.DetailScreen
import com.caique.aetnatestflickr.feature.list.presentation.ListViewModel
import com.caique.aetnatestflickr.feature.list.presentation.PhotoList
import com.caique.aetnatestflickr.ui.Screen
import com.caique.aetnatestflickr.ui.getDetailPhoto

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
        ) {
            val photo = navController.getDetailPhoto()
            photo?.let{ DetailScreen(photo) }
        }
  }
}