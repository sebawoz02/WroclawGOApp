package com.example.wroclawgoapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationBar(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object Entertainment : BottomNavigationBar(
        route = "entertainment",
        title = "Entertainment",
        icon = Icons.Default.Event
    )

    object Transport : BottomNavigationBar(
        route = "map",
        title = "Map",
        icon = Icons.Default.Map
    )

    object Favorite : BottomNavigationBar(
        route = "transport",
        title = "Transport",
        icon = Icons.Default.Traffic
    )
}
