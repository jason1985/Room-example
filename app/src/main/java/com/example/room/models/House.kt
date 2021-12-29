package com.example.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class House(
    val streetName: String,
    val houseNumber: Int,
    val askingPrice: Int,
    val size: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
//    var id: Long = 0L
    override fun toString(): String {
        return "$streetName $houseNumber price: $askingPrice size: $size $id"
    }
}
