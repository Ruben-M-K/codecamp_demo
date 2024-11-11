package com.example.cc_demo.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherData(
    @PrimaryKey val uid: Int = 1,
    @ColumnInfo(name = "temperature") val temperature: Double?,
)