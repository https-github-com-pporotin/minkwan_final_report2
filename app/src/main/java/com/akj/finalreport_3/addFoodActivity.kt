package com.akj.finalreport_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akj.finalreport_3.databinding.ActivityAddFoodBinding
import com.google.firebase.firestore.FirebaseFirestore

class addFoodActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddFoodBinding   // 뷰 바인딩
    val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언
    val foodList = arrayListOf<Foods>()    // 리스트 아이템 배열
    val adapter = FoodAdapter(foodList)         // 리사이클러 뷰 어댑터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvFood.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvFood.adapter = adapter

        db.collection("Foods")
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                foodList.clear()
                for (document in result) {  // 가져온 문서들은 result에 들어감
                    val item = Foods(document["name"] as String, document["cal"] as String)
                    foodList.add(item)
                }
                adapter.notifyDataSetChanged()  // 리사이클러 뷰 갱신
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }
    }






    }
