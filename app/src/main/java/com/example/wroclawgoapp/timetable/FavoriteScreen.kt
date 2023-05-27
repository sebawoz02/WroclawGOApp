package com.example.wroclawgoapp


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wroclawgoapp.timetable.TimeTable


@Composable
fun SelectTimeTable(){
    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color = Color.Gray)
                .clickable {
                    MainActivity.timeTableViewModel.updateTimeTable(TimeTable.ROUTE)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text = "ROUTES",fontSize = 30.sp, color = Color.White)
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color = Color.DarkGray)
                .clickable {
                    MainActivity.timeTableViewModel.updateTimeTable(TimeTable.STOP)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text = "STOPS", fontSize = 30.sp, color = Color.White)
        }

    }


}

@Composable
fun SelectStop(){
    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value

    Column() {
        Button(onClick ={ MainActivity.timeTableViewModel.clear()}, modifier = Modifier){
            Text("Go back")
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ){
            items(uiState.stops.size){idx ->
                Text(
                    modifier = Modifier
                        .clickable{
                            MainActivity.timeTableViewModel.updateSelectedStop(uiState.stops[idx])
                        },
                    text = uiState.stops[idx].stop_name
                )
            }
        }
    }


}

@Composable
fun SelectRoute(){
    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value

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



}

@Composable
fun StopList(){
    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value

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

@Composable
fun RouteList(){
    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value
}

@Composable
fun FavoriteScreen(){

    val uiState = MainActivity.timeTableViewModel.uiState.collectAsState().value

    if(uiState.timeTable == null){
        Log.d("siema","1")
        SelectTimeTable()
    }

    else if(uiState.timeTable == TimeTable.ROUTE){
        if(uiState.selectedRoute == null){
            Log.d("siema","2")
            SelectRoute()
        }else{
            Log.d("siema","3")
            StopList()
        }
    }

    else{
        if(uiState.selectedStop == null){
            Log.d("siema","4")
            SelectStop()
        }else{
            Log.d("siema","5")
            RouteList()
        }
    }

}