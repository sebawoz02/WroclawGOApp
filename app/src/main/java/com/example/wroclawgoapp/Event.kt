package com.example.wroclawgoapp

import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.util.UUID

data class Content(
    val longName: String = "Wrocław Wydarzenie",
    val url: String = "",
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

data class Location(
    val name: String = "Wrocław",
    val longitude: Double = 0.0,
    val latitude: Double = 0.0
)

data class Event(
    val id: UUID = UUID.randomUUID(),
    val name: String = "Event",
    val content: Content = Content(),
    val time: TimeData = TimeData(),
    val img: ImgData = ImgData(),
    val location: Location = Location()
)

val TEST_UUID: UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")

val debugDB = listOf(
    Event(),Event(),Event(),Event(),
    Event(),Event(),Event(),Event(),
    Event(),Event(),Event(),Event(),
    Event(),Event(),Event(),Event(),
    Event(name="Wydarzenie-1"),Event(name="Wydarzenie-1"),Event(name="Wydarzenie-1"),Event(name="Wydarzenie-1"),
    Event(name="Wydarzenie-1"),Event(name="Wydarzenie-1"),Event(name="Wydarzenie-1"),Event(name="Wydarzenie-1"),
    Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),Event(name="Wydarzenie-2"),
    Event(name="Wydarzenie-3"),Event(name="Wydarzenie-3"),Event(name="Wydarzenie-3"),Event(name="Wydarzenie-3"),
    Event(name="Wydarzenie-34"),Event(name="Wydarzenie-34"),Event(name="Wydarzenie-3"),Event(name="Wydarzenie-345"),
    Event(
        name="Jarmark Świętojański...",
        id = TEST_UUID,
        img = ImgData(url = "https://static.wikia.nocookie.net/amogus/images/c/c2/Le_monk.png/revision/latest?cb=20210830232252"),
        time = TimeData(
            startDate = LocalDate.of(2023,6,19),
            endDate = LocalDate.of(2023, 6, 27)
        ),
        content = Content(
            longName = "Jarmark Świętojański 2023",
            url = "https://www.wroclaw.pl/go/wydarzenia/rozrywka/1263446-jarmark-swietojanski?termin=1258248"
        )
    )
)

val favouriteDB = listOf<Event>(

)