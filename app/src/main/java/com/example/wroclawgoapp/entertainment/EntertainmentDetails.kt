package com.example.wroclawgoapp.entertainment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.wroclawgoapp.MainActivity

@Composable
fun EntertainmentDetails(event: MutableState<Event?>, modifier: Modifier = Modifier){
    val value = event.value!!
    val details: Details = MainActivity.dao.getDetailsFromDb(value.id!!)
    val description: List<Description> = MainActivity.dao.getDescriptionFromDb(value.id)
    val coordinates: Coordinates = MainActivity.dao.getCoordinatesFromDb(value.id)
    Box(modifier = modifier)
    {
        Column(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = value.img,
                contentDescription = value.imgDesc,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(.35f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = details.fullName,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Divider(Modifier.height(2.dp))
            LazyColumn(
                modifier = Modifier.weight(0.8f, false)
            ) {
                items(description) {
                    Text(text = it.content, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.1f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val textModifier = Modifier.width(100.dp)
                Button(
                    onClick = {
                        event.value = null
                    }
                ) {
                    Text(text = "Back", modifier = textModifier, textAlign = TextAlign.Center)
                }
                Button(
                    onClick = {
                        println(
                            "[DEBUG] lat=${coordinates.latitude} lon=${coordinates.longitude}"
                        )
                    }
                ) {
                    Text(text = "Location", modifier = textModifier, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun EntertainmentDetailsPreview(){
//    val event: Event = debugDB.find {
//        it.id == TEST_UUID
//    }!!
//
//    EntertainmentDetails(event = event)
}