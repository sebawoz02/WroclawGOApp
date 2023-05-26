package com.example.wroclawgoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun EntertainmentDetails(event: Event){
    Column() {
        AsyncImage(
            model = event.img.url,
            contentDescription = event.img.description,
            modifier = Modifier
        )
        Text(
            text = event.content.longName
        )
    }
}

@Preview(showBackground = false)
@Composable
fun EntertainmentDetailsPreview(){
    val event: Event = debugDB.find {
        it.id == TEST_UUID
    }!!

    EntertainmentDetails(event = event)
}