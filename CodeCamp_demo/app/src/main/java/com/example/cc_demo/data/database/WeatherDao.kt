package com.example.cc_demo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cc_demo.data.model.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weatherdata")
    fun getWeather(): Flow<WeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: com.example.cc_demo.data.database.model.WeatherData?)

}