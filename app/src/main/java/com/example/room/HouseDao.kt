package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.room.models.House

@Dao
interface HouseDao {
    @Query("select * from house")
    fun getAllHouses(): LiveData<List<House>>

    @Insert
    fun addHouse(house: House)

    @Delete
    fun delHouse(house: House)

    @Query("DELETE FROM house WHERE streetName = :streetName")
    fun deleteByStreetName(streetName: String)


    @Query("DELETE FROM house")
    fun deleteAllHouses()
}