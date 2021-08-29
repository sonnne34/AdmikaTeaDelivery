package com.example.adminkatea.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.adminkatea.model.ItemMenu
import com.google.firebase.storage.FirebaseStorage
import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.dialog.CountDialog
import com.newAdmilaTea.newadmilatea.model.CatMenuModel
import com.newAdmilaTea.newadmilatea.model.MenuModelcatMenu
import com.newAdmilaTea.newadmilatea.singleton.BasketSingleton

class MenuAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mItemMenuList: ArrayList<MenuModelcatMenu> = ArrayList()

    private val LAYOUT_HEADER = 0
    private val LAYOUT_CHILD = 1

    private val glide = Glide.with(context)

    @SuppressLint("NotifyDataSetChanged")
    fun setupMenu(menuList: ArrayList<CatMenuModel>){
        mItemMenuList.clear()
        for(categoryModel in menuList) {
            var model = MenuModelcatMenu()
            model.CategoryName = categoryModel.CategoryName
            model.CategoryNameENG = categoryModel.CategoryNameENG
            Log.d("HE","${model.CategoryNameENG}")
            model.isHeader = true
            mItemMenuList.add(model)

            for (i in categoryModel.Items) {
                var item = MenuModelcatMenu()
                item.Items = i.value
                mItemMenuList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (mItemMenuList[position].isHeader) {
            return LAYOUT_HEADER
        } else {
            return LAYOUT_CHILD
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LAYOUT_HEADER) {
            var layoutInflater = LayoutInflater.from(parent.context)
            var itemView = layoutInflater.inflate(R.layout.category_item_menu, parent, false)
            return HeaderViewHolder(itemView = itemView)

        } else {

            var layoutInflater = LayoutInflater.from(parent.context)
            var itemView = layoutInflater.inflate(R.layout.item_menu_recyclerview, parent, false)
            return MenuViewHolder(itemView = itemView)
        }
    }



    override fun getItemCount(): Int {
        return mItemMenuList.count()

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == LAYOUT_HEADER) {
            if (holder is HeaderViewHolder) {
                holder.bindHeader(menuCategoryModel = mItemMenuList[position])
            } else {
            }

        } else {
            if (holder is MenuViewHolder) {
                holder.bindMenu(menuCategoryModel = mItemMenuList[position])
            }
        }
    }
    class HeaderViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var categoryHeader: TextView = itemView.findViewById(R.id.category_item_menu)

        fun bindHeader(menuCategoryModel: MenuModelcatMenu) {

            Log.d("FFF","${menuCategoryModel.CategoryName}")
            Log.d("FFF","${menuCategoryModel.CategoryNameENG}")
            categoryHeader.text = "${menuCategoryModel.CategoryName}"

        }
    }

   inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var name: TextView = itemView.findViewById(R.id.text_roll)
        private var discription: TextView = itemView.findViewById(R.id.discription_text)
        private var cost: TextView = itemView.findViewById(R.id.txt_roll_price)
        private var checkBoxItem: TextView = itemView.findViewById(R.id.checkBoxItem)
        private var imgDish: ImageView = itemView.findViewById(R.id.image_dish_menu)
        private var wt: TextView = itemView.findViewById(R.id.txt_roll_weight)

        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bindMenu(menuCategoryModel: MenuModelcatMenu) {

            name.text = "${menuCategoryModel.Items?.Name}"
            discription.text = "${menuCategoryModel.Items?.Description}"
            cost.text = "${menuCategoryModel.Items?.Cost}" + " р."

            val wtVal = menuCategoryModel.Items?.Wt
            if(wtVal?.toInt() == 0){
                wt.visibility = View.GONE
            }else {
                wt.visibility = View.VISIBLE
                wt.text = "$wtVal гр."
            }
            if (menuCategoryModel.Items?.PictureForLoad == null) {
                val storage = FirebaseStorage.getInstance()
                val storageRef = storage.getReferenceFromUrl(menuCategoryModel.Items?.Picture!!)
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    menuCategoryModel.Items?.PictureForLoad = uri
                    val img = glide.load(uri)
                    img.diskCacheStrategy(DiskCacheStrategy.NONE)
                    img.into(imgDish)
                }
            } else {
                val img = glide.load(menuCategoryModel.Items?.PictureForLoad)
                img.diskCacheStrategy(DiskCacheStrategy.NONE)
                img.into(imgDish)
            }




            itemView.setOnClickListener {
                Log.d("ERA","${menuCategoryModel.CategoryNameENG}")
                CountDialog.openDialog(itemView.context, menuCategoryModel)
            }

            Log.d("Color", "menu = " + menuCategoryModel)

            var rr = BasketSingleton.checkingThelist(menuCategoryModel)
            Log.d("Color", "Bolean = " + rr)

            if (rr == true) {
                checkBoxItem.setBackgroundResource(R.color.qgreen)
            } else {
                checkBoxItem.setBackgroundResource(R.color.transparent)
            }
        }
    }

    fun scrollToCategory(name: String): Int {
        var position = 0
        for (i in mItemMenuList.indices) {
            val element: MenuModelcatMenu = mItemMenuList[i]
            if (element.isHeader) {
                if (element.CategoryName.equals(name)) {
                    position = i
                }
            }
        }
        return position
    }

}