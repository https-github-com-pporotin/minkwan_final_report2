package com.akj.finalreport_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class getAgeActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var et3: EditText
    var age: String = ""
    var height: String = ""
    var weight: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_age)

        button = findViewById(R.id.button)
        et1 = findViewById(R.id.etgetage)
        et2 = findViewById(R.id.etgetheight)
        et3 = findViewById(R.id.etgetweight)


        button.setOnTouchListener { v, event ->
            age = et1.text.toString()
            height = et2.text.toString()
            weight = et3.text.toString()
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("check", true)
            profile = true
            startActivity(intent)
            false
        }


    }


}