package com.example.wroclawgoapp.timetable

import com.squareup.moshi.Json

data class Stop(
    @Json(name = "stop_name")
    val stop_name: String,

    @Json(name = "stop_lat")
    val stop_lat: Double,

    @Json(name = "stop_lon")
    val stop_lon: Double
)
