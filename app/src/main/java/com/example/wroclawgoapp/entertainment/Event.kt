package com.example.wroclawgoapp.entertainment

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import org.jetbrains.annotations.NotNull
import java.sql.Date
import java.time.LocalDate
import java.time.LocalTime

data class Content(
    val longName: String = "Wrocław Wydarzenie",
    val url: String = "",
    val description: Array<String> = arrayOf()
)

data class TimeData(
    val startDate: LocalDate = LocalDate.now(),
    val startTime: LocalTime = LocalTime.now(),
    val isTimed: Boolean = false,
    val endDate: LocalDate = LocalDate.now(),
    val endTime: LocalTime = LocalTime.now()
)

data class ImgData(
    val url: String = "https://www.wroclaw.pl/beta2/files/news/41802/main/post-wroclaw_1.jpg",
    val description: String = "Image of Wrocław"
)

@Entity
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val name: String = "Event",
    val location: String = "Wrocław",
    val date: Date = Date.valueOf("2022-11-09"),
    val img: String = "https://www.wroclaw.pl/beta2/files/news/41802/main/post-wroclaw_1.jpg",
    val imgDesc: String = "Image of Wrocław"
)

@Entity
data class Coordinates(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val event_id: Int = 0,
    val longitude: Double = 0.0,
    val latitude: Double = 0.0
)

@Entity
data class Details(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val event_id: Int = 0,
    val fullName: String = "Wydarzenie we Wrocławiu",
    val time: String = "12:00"
)

@Entity
data class Description(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val event_id: Int = 0,
    val content: String = ""
)

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

const val TEST_UUID: Int = 666

val debugDB = listOf(
    Event(),
    Event(name="Wydarzenie-1"),
    Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-3"),
    Event(name="Wydarzenie-34"),
    Event(
        name="Jarmark Świętojański...",
        id = TEST_UUID,
        img = "https://i.ytimg.com/vi/3VC6lRgq7hc/sddefault.jpg",
        date = Date.valueOf("2023-06-19")
//        time = TimeData(
//            startDate = LocalDate.of(2023,6,19),
//            endDate = LocalDate.of(2023, 6, 27)
//        ),
//        content = Content(
//            longName = "Jarmark Świętojański 2023",
//            url = "https://www.wroclaw.pl/go/wydarzenia/rozrywka/1263446-jarmark-swietojanski?termin=1258248",
//            description = arrayOf(
//                "Jarmark Świętojański 2023 odbędzie się w terminie od 19 maja do 27 czerwca. Stoiska otwarte będą od godz. 10.00 do 21.00, sprzedaż artykułów będzie prowadzone również online na pojawi.pl",
//                "Jarmark Świętojański odbędzie się na wrocławskim Rynku – w obszarze wokół Pręgierza, ulicy Oławskiej od ulicy Szewskiej do Rynku i Świdnickiej od przejścia podziemnego do Rynku.",
//                "Sprzedaż na Jarmarku Świętojańskim odbywać się będzie wyłącznie z domków drewnianych. Nie ma możliwości prowadzenia sprzedaży z namiotów, foodtrucków lub ustawiania indywidualnych ekspozycji i prowadzenia sprzedaży poza domkiem.",
//                "Artykuły przemysłowe, rękodzieło i rzemiosło – artystyczne wyroby własne, dekoracje i ozdoby, ceramika, biżuteria, mydła i kosmetyki naturalne, ręcznie szyte zabawki z naturalnych materiałów, wyroby regionalne (w tym góralskie), kubki pamiątkowe z nadrukiem, pamiątki, wyroby ze szkła, świece, metaloplastyka, galanteria, torby, odzież artystyczna.",
//                "Artykuły spożywcze – regionalne sery i wędliny, pieczywo i wypieki, konfitury, miody, nalewki, wyroby czekoladowe i słodycze, zioła i przyprawy, kawy i herbaty, produkty naturalne i wegańskie."
//                )
//        )
    )
)

val favouriteDB = listOf<Event>(

)