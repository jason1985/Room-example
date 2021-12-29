package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.room.models.House
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val etStreetName = findViewById<EditText>(R.id.et_street_name)
        val etNumber = findViewById<EditText>(R.id.et_number)
        val etPrice = findViewById<EditText>(R.id.et_price)
        val etSize = findViewById<EditText>(R.id.et_size)
        val btnAdd = findViewById<Button>(R.id.btn_add)

        // setup db
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "AppDatabase"
        )
//            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        // update listView based on db contents
        db.houseDao().getAllHouses().observe(this,
            Observer<List<House?>?> {
                listView.adapter = ArrayAdapter<House>(this,android.R.layout.simple_list_item_1,it)
            })

        // add new data to db
        btnAdd.setOnClickListener {
            val house = House(etStreetName.text.toString(),parseInt(etNumber.text.toString()),
                parseInt(etPrice.text.toString()), parseInt(etSize.text.toString()))
            Thread {
                db.houseDao().addHouse(house)
            }.start()
        }

//        Thread {
//            val h = House("test",1234,1234,1234)
//            h.id = 50
//            db.houseDao().delHouse(h) // exact match
//            db.houseDao().deleteByStreetName("first")
//            db.houseDao().deleteAllHouses()
//        }.start()

    }
}

