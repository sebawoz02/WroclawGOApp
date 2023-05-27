package com.example.wroclawgoapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wroclawgoapp.entertainment.EntertainmentScreen
import com.example.wroclawgoapp.timetable.RouteViewModel
import java.net.URL
import java.time.LocalDate

@Composable
fun BottomNavGraph(navController: NavHostController, routeViewModel: RouteViewModel) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationBar.Entertainment.route
    ){
        composable(route = BottomNavigationBar.Entertainment.route){
            EntertainmentScreen()
        }
        composable(route = BottomNavigationBar.Transport.route){
            TransportScreen()
        }
        composable(route = BottomNavigationBar.Favorite.route){
            FavoriteScreen(routeViewModel)
        }
    }
}