package com.example.wroclawgoapp

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wroclawgoapp.entertainment.*
import com.example.wroclawgoapp.timetable.*
import com.example.wroclawgoapp.ui.theme.WroclawGOAppTheme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


@Database(entities = [Event::class, Details::class, Description::class, Coordinates::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "events-40"
        ).createFromAsset("database.db").allowMainThreadQueries().build()
        dao = db.eventDao();
        dao.getEventsFromDb("");

        val dataProvider = DataProvider(applicationContext)
        val routesJsonArray = dataProvider.getJSONArray("routes.txt")

        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val routeAdapter = moshi.adapter(Route::class.java)

        val routes = mutableListOf<Route>()
        for(obj in routesJsonArray) routes.add( routeAdapter.fromJson(obj)!! )

        timeTableViewModel = TimeTableViewModel(routes)

        super.onCreate(savedInstanceState)
        setContent {
            WroclawGOAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    companion object {
        lateinit var dao: EventDao
        lateinit var timeTableViewModel: TimeTableViewModel
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WroclawGOAppTheme {
        Greeting("Android")
    }
}

