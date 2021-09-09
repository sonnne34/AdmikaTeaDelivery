package com.example.adminkatea.adapter

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.newAdmilaTea.newadmilatea.R

import com.newAdmilaTea.newadmilatea.model.MenuModelcatMenu
import com.newAdmilaTea.newadmilatea.model.ModelOrder

class OrderAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mItemCheckList: ArrayList<ModelOrder> = ArrayList()



    @SuppressLint("NotifyDataSetChanged")
    fun setupOrderAdapter(checkList: ArrayList<ModelOrder>){
        mItemCheckList.clear()
        mItemCheckList.addAll(checkList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.items_order, parent, false)
        return Holderitem(itemView = itemView)
    }



    override fun getItemCount(): Int {
        return mItemCheckList.count()

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holderitem){
            holder.bind(itemView = mItemCheckList[position])
        }
    }

   class Holderitem(itemView : View) : RecyclerView.ViewHolder(itemView){
       var textNumber = itemView.findViewById(R.id.text_number) as TextView
       var textSourse = itemView.findViewById(R.id.textSourse) as TextView
       var textMoney = itemView.findViewById(R.id.money) as TextView
       var textDescription = itemView.findViewById(R.id.text_description) as TextView
      fun bind(itemView: ModelOrder){
          textNumber.text = "1"
          textSourse.text = itemView.source
          textMoney.text = itemView.money
          textDescription.text = itemView.order


       }
   }


}