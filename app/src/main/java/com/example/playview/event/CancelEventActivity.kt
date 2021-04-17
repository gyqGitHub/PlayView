package com.example.playview.event

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.playview.R
import kotlinx.android.synthetic.main.activity_cancel_evant.*

class CancelEventActivity : AppCompatActivity() {
    val data = arrayListOf("1","2","3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_evant)

        btn_remove_view.setOnTouchListener { v, event ->
            Thread.sleep(6000)
            if (event.action == MotionEvent.ACTION_MOVE){
                cl_root.removeView(btn_remove_view)
            }
            return@setOnTouchListener true
        }

        btn_test2.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE){
                btn_test2.visibility = View.GONE
            }
            Log.e("gyq","触摸了-->" + event.action)

            return@setOnTouchListener true
        }

        btn_touch_out.setOnTouchListener { v, event ->
            Log.e("gyq","触摸子View范围之外,依然能接收到触摸事件 -->" + event.action )
            return@setOnTouchListener true
        }

        rv.run {
            layoutManager = LinearLayoutManager(this@CancelEventActivity)
            adapter = object : RecyclerView.Adapter<MyViewHolder>(){
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): MyViewHolder {
                    return MyViewHolder(View.inflate(this@CancelEventActivity,R.layout.adapter_cancel_rv_test_item,null))
                }

                override fun getItemCount(): Int {
                    return data.size
                }

                override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                    holder.item.text = "item ${data[position]}"
                }

            }
            postDelayed({
                data[0] = "gyq"
                adapter?.notifyDataSetChanged()
            },10000)
        }
    }

    private class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_item)
        }
    }
}