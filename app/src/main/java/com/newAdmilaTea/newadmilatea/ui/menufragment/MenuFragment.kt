package com.newAdmilaTea.newadmilatea.ui.menufragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkatea.adapter.MenuAdapter
import com.example.adminkatea.model.ItemMenu
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import com.newAdmilaTea.newadmilatea.databinding.FragmentMenuBinding
import com.newAdmilaTea.newadmilatea.model.CatMenuModel


class MenuFragment : Fragment() {


    private lateinit var binding : FragmentMenuBinding

    private lateinit var menuAdapter : MenuAdapter
    private  var menulist : ArrayList<ItemMenu> = ArrayList()
    private lateinit var recyclerView : RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        menuAdapter = MenuAdapter()
        binding.recuclerMenu.adapter = menuAdapter
        binding.recuclerMenu.layoutManager = LinearLayoutManager(binding.root.context,RecyclerView.VERTICAL,false)
        binding.recuclerMenu.setHasFixedSize(true)
        binding.recuclerMenu.recycledViewPool.setMaxRecycledViews(100, 100)
        binding.recuclerMenu.setItemViewCacheSize(300)
        binding.recuclerMenu.isDrawingCacheEnabled = true
        loadMenu()
        var tt = ItemMenu()
        tt.Description = "GGG"
        var ee = ItemMenu()
        ee.Description = "LLL"

        menulist.add(tt)
        menulist.add(ee)

        updateAdapter(menulist)

        return binding.root
    }

    private fun updateAdapter(menuList : ArrayList<ItemMenu>) {
        menuAdapter.setupMenu(menuList)
    }



    private fun loadMenu() {


        val database = FirebaseDatabase.getInstance()

        val myRef = database.getReference("RestaurantsMenu/TeaTemple")

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for (ds in dataSnapshot.children) {

                    val value = ds.getValue(CatMenuModel::class.java)!!

                    Log.d("RESS", "value = 5" + value.CategoryName)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("dima", "Failed to read value.", error.toException())
            }
        })
    }


}