package com.example.wroclawgoapp.timetable

import com.squareup.moshi.Json

data class Route(
    @Json(name = "route_short_name")
    val route_id: String,

    @Json(name = "route_desc")
    val route_desc: List<String>

)