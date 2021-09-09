package com.newAdmilaTea.newadmilatea.ui.reportfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkatea.adapter.MenuAdapter
import com.example.adminkatea.adapter.OrderAdapter
import com.google.firebase.database.*
import com.newAdmilaTea.newadmilatea.databinding.FragmentReportBinding
import com.newAdmilaTea.newadmilatea.model.CatMenuModel
import com.newAdmilaTea.newadmilatea.model.ModelOrder


class ReportFragment : Fragment() {


   lateinit var binding: FragmentReportBinding

   lateinit var orderAdapter : OrderAdapter
    var list : ArrayList<ModelOrder> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater)



        orderAdapter = OrderAdapter()

        binding.recyclerOrder.adapter = orderAdapter
        binding.recyclerOrder.layoutManager = LinearLayoutManager(binding.root.context,
            RecyclerView.VERTICAL,false)
        binding.recyclerOrder.setHasFixedSize(true)
        binding.recyclerOrder.recycledViewPool.setMaxRecycledViews(100, 100)
        binding.recyclerOrder.setItemViewCacheSize(300)
        binding.recyclerOrder.isDrawingCacheEnabled = true





        loadOrder()




        return binding.root
    }

    private fun loadOrder() {
        val database = FirebaseDatabase.getInstance()

        val myRef = database.getReference("Orders/Tyumen/TeaTemple/2021/7")


        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for (ds in dataSnapshot.children) {
                    val value = ds.getValue(ModelOrder::class.java)!!



                    list.add(value)
                }
                updateAdapter(list)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("dima", "Failed to read value.", error.toException())
            }
        })
    }

    private fun updateAdapter(list : ArrayList<ModelOrder>) {
        orderAdapter.setupOrderAdapter(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}