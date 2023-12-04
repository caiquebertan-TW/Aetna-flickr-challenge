package com.caique.aetnatestflickr.ui

import androidx.navigation.NavController
import com.caique.aetnatestflickr.data.model.PhotoItem
import com.caique.aetnatestflickr.util.toJson

sealed class Screen(val route: String) {
  data object HomeList : Screen("home_list")
  data object Detail : Screen("detail/$ARG_DETAIL_PHOTO")
}

const val ARG_DETAIL_PHOTO = "photo"


fun NavController.navigateToDetail(photo: PhotoItem) {
  navigate(Screen.Detail.route.replace(ARG_DETAIL_PHOTO, photo.toJson()))
}