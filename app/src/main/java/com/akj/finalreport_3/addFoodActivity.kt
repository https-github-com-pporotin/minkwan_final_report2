package com.akj.finalreport_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class addFoodActivity : AppCompatActivity() {

    lateinit var rv_food : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val foodList = arrayListOf(
            Foods(R.drawable.whiterice,"흰쌀밥","1.25"),
            Foods(R.drawable.chicken_breast,"닭가슴살","3.2"),

        )

        rv_food = findViewById(R.id.rv_food)

        rv_food.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_food.setHasFixedSize(true) //리사이클러뷰에 대한 성능 개선 방안?

        rv_food.adapter = FoodAdapter(foodList)






    }
}