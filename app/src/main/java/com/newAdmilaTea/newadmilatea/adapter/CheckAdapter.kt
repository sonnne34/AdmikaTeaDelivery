package com.example.adminkatea.adapter

import android.annotation.SuppressLint
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.dialog.CountDialog

import com.newAdmilaTea.newadmilatea.model.MenuModelcatMenu
import com.newAdmilaTea.newadmilatea.singleton.BasketSingleton

class CheckAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mItemCheckList: ArrayList<MenuModelcatMenu> = ArrayList()



    @SuppressLint("NotifyDataSetChanged")
    fun setupCheck(checkList: ArrayList<MenuModelcatMenu>){
        mItemCheckList.clear()
        mItemCheckList.addAll(checkList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var itemView = layoutInflater.inflate(R.layout.item_check_adapter, parent, false)
        return Holderitem(itemView = itemView)
    }



    override fun getItemCount(): Int {
        return mItemCheckList.count()

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holderitem){
            holder.bind(itemView1 = mItemCheckList[position])
            btnDel(holder, position)
        }
    }

   class Holderitem(itemView : View) : RecyclerView.ViewHolder(itemView){
       var  textname: TextView = itemView.findViewById(R.id.text_name_item)
       var  costText: TextView = itemView.findViewById(R.id.lastCost_text)
       var  textlastCost: TextView = itemView.findViewById(R.id.lastCost)
       var  textnewCost: TextView = itemView.findViewById(R.id.newCost)
       var  btnDel: Button = itemView.findViewById(R.id.btn_del_)
       var  stop: TextView = itemView.findViewById(R.id.stop)
       var  stopActive: TextView = itemView.findViewById(R.id.stop_active)

      @SuppressLint("SetTextI18n")
      fun bind(itemView1: MenuModelcatMenu){
          textname.text = itemView1.Item?.Name
          textlastCost.text = itemView1.Item?.Cost.toString() + " р."
          textnewCost.text = itemView1.Item?.NewCost.toString() + " р."
          val stopVal = itemView1.Item?.Stop?.toInt()!!
          val switch = itemView1.Item?.Switch

          //если изменение цены не происходит то цены скрыть
          if (switch == 0){
              costText.visibility = View.GONE
              textlastCost.visibility = View.GONE
              textnewCost.visibility = View.GONE

          } else {
              costText.visibility = View.VISIBLE
              textlastCost.visibility = View.VISIBLE
              textnewCost.visibility = View.VISIBLE
          }

          //условия видимости объектов "стоп" и "активировать"
          when (stopVal) {
              0 -> {
                  stop.visibility = View.VISIBLE
                  stopActive.visibility = View.GONE
              }
              1 -> {
                  stop.visibility = View.GONE
                  stopActive.visibility = View.VISIBLE
              }
              else -> {
                  stop.visibility = View.GONE
                  stopActive.visibility = View.GONE
              }
          }


          itemView.setOnClickListener {

              CountDialog.openDialog(itemView.context, itemView1)

          }
       }
   }

    private fun btnDel(holder: RecyclerView.ViewHolder, position: Int){
        if(holder is Holderitem ){
            holder.bind(itemView1 = mItemCheckList[position])
            holder.btnDel.setOnClickListener {

                val delPosDialog = AlertDialog.Builder(holder.itemView.context
                )
                delPosDialog.setTitle("Аннигилирование")
                delPosDialog.setMessage("Удалить блюдо?")
                delPosDialog.setPositiveButton(
                    "Да"
                ) { _, _ ->
                    BasketSingleton.delPos(position)
                    BasketSingleton.notifyTwo()
                    Log.d("list", "del= ${mItemCheckList}")
                }
                delPosDialog.setNegativeButton(
                    "Ой, нет!"
                ) { _, _ -> }
                delPosDialog.show()
            }
        }
    }


}