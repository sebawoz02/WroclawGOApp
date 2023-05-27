package com.example.wroclawgoapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteScreen(){
    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value

    if(uiState.selectedRoute==null){
        Column() {
            Button(onClick ={ MainActivity.timeTableViewModel.clear()}, modifier = Modifier){
                Text("Go back")
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(10)
            ){
                items(uiState.routes.size){idx ->
                    Text(
                        modifier = Modifier
                            .clickable{
                                MainActivity.timeTableViewModel.updateSelectedRoute(uiState.routes[idx])
                            },
                        text = uiState.routes[idx].route_id
                    )
                }
            }
        }
    }else{
        Column() {
            Button(onClick ={ MainActivity.timeTableViewModel.clear()}, modifier = Modifier){
                Text("Go back")
            }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ){
                items(uiState.selectedRoute!!.route_desc.size){ idx ->
                    Text(
                        text = uiState.selectedRoute.route_desc[idx]
                    )
                }
            }
        }
    }
}