package com.akj.finalreport_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//어댑터는 중간 브릿지역할

class FoodAdapter(val foodlist: ArrayList<Foods>) : RecyclerView.Adapter<FoodAdapter.CustomViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.CustomViewHolder {
            //activity의 oncreate의 setcontentview랑 비슷하다. 즉 연결될 화면이 뭔가?
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustomViewHolder(view)
        //생성해준 뷰를 커스텀뷰홀더에 전달한다.
    }

    override fun onBindViewHolder(holder: FoodAdapter.CustomViewHolder, position: Int) {
        //뷰를 가져다가 실제 연결하는 메소드
        holder.foodimage.setImageResource(foodlist.get(position).foodimage)
        holder.foodname.text = foodlist.get(position).name
        holder.foodcal.text = foodlist.get(position).cal

        //계속 실행되는 곳이다.
        //포지션은 리스트뷰 완성했을때의 화면??
    }

    override fun getItemCount(): Int {
        //리스트들에 대한 총 개수를 반환한다.
        return foodlist.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodimage = itemView.findViewById<ImageView>(R.id.ivicon)   //음식사진
        val foodname = itemView.findViewById<TextView>(R.id.tvfoodname) //음식이름
        val foodcal = itemView.findViewById<TextView>(R.id.tvfoodcal)   //음식칼로리
    }  //내부 클래스 뷰에 대한걸 꽉 잡아주는 역할

}