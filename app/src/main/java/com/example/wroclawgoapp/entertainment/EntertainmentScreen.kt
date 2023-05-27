package com.example.wroclawgoapp.entertainment

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.wroclawgoapp.MainActivity
import com.example.wroclawgoapp.R
import com.example.wroclawgoapp.ui.theme.WroclawGOAppTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.*
import java.time.format.DateTimeFormatter


@Composable
fun EntertainmentScreen(){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        contentAlignment = Alignment.Center
    ){
        var searchBarText = remember { mutableStateOf("") }
        var searchBarActive = remember { mutableStateOf(false) }
        var history = remember {
            mutableSetOf<String>()
        }
        var eventActive = remember {
            mutableStateOf<Event?>(null)
        }
        var items by remember {
            mutableStateOf<List<Event>>(emptyList())
        }

        items = MainActivity.dao.getEventsFromDb(searchBarText.value)

        if(items.size > 0){
            println("item[0]: ${items[0].toString()}")
        }


        if(eventActive.value == null) {
            Scaffold(
                modifier = Modifier,
                content = {
                    if (items.isNotEmpty())
                        Content(items, activeEvent = eventActive)
                    else
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No results for ${searchBarText.value}...",
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                },
                topBar = {
                    MySearchBar(history = history, text = searchBarText, active = searchBarActive)
                }
            )
        } else {
            EntertainmentDetails(event = eventActive, modifier = Modifier.padding(bottom = 60.dp))
        }
    }
}

suspend fun getResults(searchString: String): List<Event>{
    // TODO: Fetch results from backend
    val list = mutableListOf<Event>()

    for(ev in debugDB){
        if(list.size >= 20)
            break
        if(ev.name.contains(searchString, true))
            list.add(ev)
    }

//    MainActivity.dao.getEventsFromDb(searchString).collectAsState(initial = emptyList<Event>())

    return list.toList()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(history: MutableSet<String>, text: MutableState<String>, active: MutableState<Boolean>){
    SearchBar(
        modifier = Modifier.fillMaxWidth(1f),
        query = text.value,
        onQueryChange = {text.value = it},
        onSearch = {
            active.value = false
            history.add(text.value)
                   },
        active = active.value,
        onActiveChange = {active.value = it},
        placeholder = { Text(text = "Search")},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")},
        trailingIcon = {
            if(active.value)
                Icon(modifier = Modifier.clickable { if(text.value != "") text.value = "" else active.value = false }, imageVector = Icons.Default.Close, contentDescription = "Close Icon")
        })
    {
        history.forEach{
            Row(modifier = Modifier.padding(all = 14.dp)) {
                androidx.compose.material3.Icon(imageVector = Icons.Default.History, contentDescription = "History Icon")
                Text(text = it, modifier = Modifier.clickable { text.value = it })
            }
        }
    }
}

@Composable
fun Content(eventList: List<Event>, activeEvent: MutableState<Event?>){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(eventList){
                ListItem(event = it, modifier = Modifier.clickable {
                    activeEvent.value = it
                })
            }
        })
}

@Composable
fun ListItem(event: Event, modifier: Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(200.dp)
            .background(Color.White)
            .then(modifier)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(5.dp)
                .background(Color.LightGray)
                .padding(top = 10.dp, start = 0.dp, end = 0.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = event.img,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.65f)
                    .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(RoundedCornerShape(5.dp)),
                contentDescription = event.imgDesc,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                error = painterResource(id = R.drawable.imagenotfound)
            )
            Text(
                text = event.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 2,
                softWrap = true,
                modifier = Modifier
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = event.location)
                Text(text = event.date.toString())
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun EntertainmentScreenPreview() {
    WroclawGOAppTheme {
//        EntertainmentScreen()
//        ListItem(Event())
    }
}