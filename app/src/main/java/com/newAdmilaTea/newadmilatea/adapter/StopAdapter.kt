package com.example.adminkatea.adapter

import android.annotation.SuppressLint
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.model.LastModel

import com.newAdmilaTea.newadmilatea.model.MenuModelcatMenu

class StopAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mItemCheckList: ArrayList<LastModel> = ArrayList()



    @SuppressLint("NotifyDataSetChanged")
    fun setupStop(checkList: ArrayList<LastModel>){
       for (i in checkList){
           Log.d("YR","${i.name}")
       }

        mItemCheckList.clear()
        mItemCheckList.addAll(checkList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.item_stop_adapter, parent, false)
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
        var  textname: TextView = itemView.findViewById(R.id.name)
        var  textCost: TextView = itemView.findViewById(R.id.cost)
        var  discription: TextView = itemView.findViewById(R.id.discption_tex)
      fun bind(itemView: LastModel){
          textname.text = itemView.name

       }
   }


}