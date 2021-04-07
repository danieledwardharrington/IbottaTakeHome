package com.dharringtondev.ibottatakehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dharringtondev.ibottatakehome.data.DataReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dr = DataReader(application)
        dr.getData()
    }
}