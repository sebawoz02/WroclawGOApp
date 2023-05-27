package com.example.wroclawgoapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wroclawgoapp.timetable.RouteViewModel



@Composable
fun FavoriteScreen(viewModel: RouteViewModel){

    val uiState = viewModel.uiState.collectAsState().value

    if(uiState.selectedRoute==null){

        LazyVerticalGrid(
            columns = GridCells.Fixed(10)
        ){
            items(uiState.routes.size){idx ->
                Text(
                    modifier = Modifier
                        .clickable{
                            viewModel.updateSelectedRoute(uiState.routes[idx])
                        },
                    text = uiState.routes[idx].route_id
                )
            }
        }

    }
    else{

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ){
            items(uiState.selectedRoute.route_desc.size){ idx ->
                Text(
                    text = uiState.selectedRoute.route_desc[idx]
                )
            }
        }
    }


}