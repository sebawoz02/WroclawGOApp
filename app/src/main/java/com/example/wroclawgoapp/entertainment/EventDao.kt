package com.example.wroclawgoapp.entertainment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Upsert
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM event WHERE name LIKE '%' || :name || '%' LIMIT 20")
    fun getEventsFromDb(name: String): List<Event>

    @Query("SELECT * FROM details WHERE event_id = :eventId LIMIT 1")
    fun getDetailsFromDb(eventId: Int): Details

    @Query("SELECT * FROM coordinates WHERE event_id = :eventId LIMIT 1")
    fun getCoordinatesFromDb(eventId: Int): Coordinates

    @Query("SELECT * FROM description WHERE event_id = :eventId")
    fun getDescriptionFromDb(eventId: Int): List<Description>
}