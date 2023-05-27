package com.example.wroclawgoapp

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun TransportScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
//        JakDojadePage(url = "https://jakdojade.pl/WROCLAW/trasa/z--Ma%C5%82opanewska-3--do--G%C5%82ogowska-24?fn=Ma%C5%82opanewska%203&fc=51.1231781:16.986767&ft=COORDINATE&tn=G%C5%82ogowska%2024&tc=51.118433211920866:17.001976328146558&tt=COORDINATE&d=26.05.23&h=15:40&ia=false&engine=DEFAULT")
        JakDojadePage(url = "https://zbiorkom.live/wroclaw")
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun JakDojadePage(url : String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl(url)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
        }
    }, update = {
        it.loadUrl(url)
    },
    modifier = Modifier.padding(bottom = 50.dp))
}