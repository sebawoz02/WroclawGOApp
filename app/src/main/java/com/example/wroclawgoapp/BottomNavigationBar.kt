package com.example.wroclawgoapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationBar(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object Entertainment : BottomNavigationBar(
        route = "entertainment",
        title = "Entertainment",
        icon = Icons.Default.Search
    )

    object Transport : BottomNavigationBar(
        route = "transport",
        title = "Transport",
        icon = Icons.Default.Place
    )

    object Favorite : BottomNavigationBar(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Default.Favorite
    )
}
