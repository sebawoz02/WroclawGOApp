package com.example.wroclawgoapp.timetable

import android.content.Context
import java.io.BufferedReader

class DataProvider(val context: Context) {

    fun getJSONArray(fileName: String): List<String> {
        val fileContent =
            context.assets.open(fileName).bufferedReader().use(BufferedReader::readText)
                .replace("\"", "")
        val arr = mutableListOf<String>()
        val lines = fileContent.split("\n")

        val tags = lines[0].split(",")
        val routes = lines.subList(1, lines.size)

        for (route in routes) {
            arr.add(toJSON(route, tags))
        }

        return arr
    }

    private fun toJSON(content: String, tags: List<String>): String{
        val values = content.split(",")
        var jsn = "{"

        for(idx in tags.indices){
            val tag = tags[idx]
            if(tag!="route_desc"){
                jsn += "\"$tag\":\"${values[idx]}\","
            }else{
                jsn += "\"$tag\":"
                jsn += "["
                val stops = values[idx].split(" - ")
                for(stop in stops) jsn += "\"$stop\","
                jsn = jsn.dropLast(1) + "],"
            }
        }

        jsn = jsn.dropLast(1) + "}"
        return jsn
    }
}