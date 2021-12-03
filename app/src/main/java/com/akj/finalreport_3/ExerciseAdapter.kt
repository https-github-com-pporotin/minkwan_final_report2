package com.akj.finalreport_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

//어댑터는 중간 브릿지역할

class ExerciseAdapter(val exerciseslist: ArrayList<Exercises>) : RecyclerView.Adapter<ExerciseAdapter.CustomViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.CustomViewHolder {
        //activity의 oncreate의 setcontentview랑 비슷하다. 즉 연결될 화면이 뭔가?
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item2,parent,false)
        return CustomViewHolder(view)
        //생성해준 뷰를 커스텀뷰홀더에 전달한다.
    }
    private lateinit var itemClickListener : OnItemClickListener //클릭리스너
    override fun onBindViewHolder(holder: ExerciseAdapter.CustomViewHolder, position: Int) {
        //뷰를 가져다가 실제 연결하는 메소드
        holder.exerciseimage.setImageResource(exerciseslist.get(position).exerciseimage)
        holder.exercisename.text = exerciseslist.get(position).name
        holder.exercisepart.text = exerciseslist.get(position).part

        holder.itemView.setOnClickListener{
            if(this::itemClickListener.isInitialized){
                itemClickListener.onClick(it, position)
            }
        }//클릭리스너

        //계속 실행되는 곳이다.
        //포지션은 리스트뷰 완성했을때의 화면??
    }

    override fun getItemCount(): Int {
        //리스트들에 대한 총 개수를 반환한다.
        return exerciseslist.size
    }

    interface OnItemClickListener {
        fun onClick(v : View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    //클릭리스너




    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseimage = itemView.findViewById<ImageView>(R.id.exercise_image)   //운동사진
        val exercisename = itemView.findViewById<TextView>(R.id.exercise_name) //운동이름
        val exercisepart = itemView.findViewById<TextView>(R.id.exercise_part) //운동부위

    }  //내부 클래스 뷰에 대한걸 꽉 잡아주는 역할
}