package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.models.House

@Database(entities = [House::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun houseDao(): HouseDao
}