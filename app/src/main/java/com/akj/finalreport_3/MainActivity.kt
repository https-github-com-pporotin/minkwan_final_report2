package com.akj.finalreport_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

var profile :Boolean = false
class MainActivity : AppCompatActivity() {
    var profileage : Int = 0
    var profileheight : Float = 0.0f
    var profileweight : Float = 0.0f
    lateinit var needcaltext : TextView
    lateinit var addfoodbtn : Button
    lateinit var addtrainbtn : Button
    lateinit var mainlayout : androidx.constraintlayout.widget.ConstraintLayout  // 메인 액티비티 정보를 받는 변수
    lateinit var Change : Button    // 임시
    lateinit var Logout : Button    // 임시
    lateinit var infoView : View    // 대화 상자 만들기 위한 변수
    lateinit var EdtAge : EditText  // 개인정보 수정 시 나이 저장변수
    lateinit var Edtheight : EditText // 개인정보 수정 시 키 저장변수
    lateinit var Edtweight : EditText // 개인정보 수정 시 체중 저장변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainlayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.MainLayout)
        needcaltext = findViewById(R.id.needcaltext)
        addfoodbtn = findViewById(R.id.addfoodbtn)
        addtrainbtn = findViewById(R.id.addtrainbtn)
        Change = findViewById<Button>(R.id.tchange) // 임시
        Logout = findViewById<Button>(R.id.tlogout)  // 임시

        if(profile==true)
        {
            profileage = intent.getStringExtra("age")!!.toInt()
            profileheight = intent.getStringExtra("height")!!.toFloat()
            profileweight = intent.getStringExtra("weight")!!.toFloat()
            savedata()
        }

        loaddata()

        if(profile == false) //프로필이 등록되지 않았다면
        {
            intent = Intent(this,getAgeActivity::class.java)
            startActivity(intent)
            profile = intent.getBooleanExtra("check",false)
        }

        if(profile==true) {
            needcaltext.text = (profileweight + (13.75 * profileheight) + (5 * profileheight) - (6.76 * profileage) * 1.5).toString()
            //오늘 섭취해야 할 칼로리 계산

            addfoodbtn.setOnClickListener {
                intent = Intent(this,addFoodActivity::class.java)
                startActivity(intent)
            }

            addtrainbtn.setOnClickListener {
                intent = Intent(this,addTrainActivity::class.java)
                startActivity(intent)
            }

            // 사용자 정보 변경 버튼
            Change.setOnClickListener {
                infoView = View.inflate(this@MainActivity, R.layout.info_change, null)
                var handle = AlertDialog.Builder(this@MainActivity)
                handle.setTitle("사용자 정보 변경")
                handle.setView(infoView)
                EdtAge = infoView.findViewById<EditText>(R.id.user_age)
                Edtheight = infoView.findViewById<EditText>(R.id.user_height)
                Edtweight = infoView.findViewById<EditText>(R.id.user_weight)

                EdtAge.setText(profileage.toString())
                Edtheight.setText(profileheight.toString())
                Edtweight.setText(profileweight.toString())


                handle.setPositiveButton("확인"){ dialog, which ->
                    profileage = Integer.parseInt(EdtAge.text.toString())
                    profileheight = Edtheight.text.toString().toFloat()
                    profileweight = Edtweight.text.toString().toFloat()
                    needcaltext.text = (profileweight + (13.75 * profileheight) + (5 * profileheight) - (6.76 * profileage) * 1.5).toString()
                }
                handle.setNegativeButton("닫기", null)
                handle.show()
            }

            Logout.setOnClickListener {
                intent = Intent(this, getAgeActivity::class.java)
                startActivity(intent)
            }
        }

    }

    // 3점 메뉴를 만들기 위한 create함수
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
//        var mInflater = menuInflater
//        mInflater.inflate(R.menu.select_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.change ->{
//
//            }
//            R.id.logout ->{
//
//            }
//        }
//        return false
//    }

    private fun loaddata()
    {
        val pref = getSharedPreferences("pref",0)
        profile = pref.getBoolean("pfcheck",false)
        profileage = pref.getInt("pfage",0)
        profileheight = pref.getFloat("pfheight",0.0f)
        profileweight = pref.getFloat("pfweight",0.0f)

    }

    private fun savedata()
    {
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit() //수정 모드
        edit.putBoolean("pfcheck",profile)
        edit.putInt("pfage",profileage)
        edit.putFloat("pfheight",profileheight)
        edit.putFloat("pfweight",profileweight)
        edit.apply()
    }


}