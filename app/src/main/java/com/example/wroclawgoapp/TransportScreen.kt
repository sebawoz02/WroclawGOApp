package com.example.wroclawgoapp

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun TransportScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
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
    })
}