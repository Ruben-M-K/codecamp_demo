package com.example.cc_demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cc_demo.data.database.model.WeatherData

@Database(entities = [WeatherData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}
