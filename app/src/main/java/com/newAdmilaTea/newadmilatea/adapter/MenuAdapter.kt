package com.example.adminkatea.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkatea.model.ItemMenu
import com.newAdmilaTea.newadmilatea.R

class MenuAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mItemMenuList: ArrayList<ItemMenu> = ArrayList()

    fun setupMenu(menuList: ArrayList<ItemMenu>){
        mItemMenuList.clear()
        mItemMenuList.addAll(menuList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_menu_adapter, parent, false)

        return CategoryViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return mItemMenuList.count()

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CategoryViewHolder){
            holder.bind(itemMenu = mItemMenuList[position])
        }
    }
    class CategoryViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.image_adapter)
        var discription: TextView = itemView.findViewById(R.id.description_tea)
        var costtea: TextView = itemView.findViewById(R.id.cost_tea)
        var gramTea: TextView = itemView.findViewById(R.id.gram_tea)

        fun bind(itemMenu: ItemMenu){
            discription.text  = itemMenu.Description.toString()
            costtea.text = itemMenu.Cost.toString()
            gramTea.text = itemMenu.Wt.toString()

        }
    }
}