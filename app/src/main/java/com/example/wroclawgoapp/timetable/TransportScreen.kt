package com.example.wroclawgoapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteScreen(){
    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value

    if(uiState.selectedRoute==null){
        Column(
            horizontalAlignment = CenterHorizontally
        ) {
            Button(onClick ={ MainActivity.timeTableViewModel.clear()}){
                Text("Go back")
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 10.dp
                ),
            ){
                items(uiState.routes.size){idx ->

                    Card(
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = uiState.routes[idx].route_id,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable{
                                MainActivity.timeTableViewModel.updateSelectedRoute(uiState.routes[idx])
                            }
                        )
                    }
                }
            }
        }
    }else{
        Column(horizontalAlignment = CenterHorizontally) {
            Button(onClick ={ MainActivity.timeTableViewModel.clear()}){
                Text("Go back")
            }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 50.dp)
            ){
                items(uiState.selectedRoute.route_desc.size){ idx ->
                    Card(
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = uiState.selectedRoute.route_desc[idx],
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }
}