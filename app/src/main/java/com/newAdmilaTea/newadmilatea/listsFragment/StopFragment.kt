package com.newAdmilaTea.newadmilatea.listsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkatea.adapter.SaleAdapter
import com.example.adminkatea.adapter.StopAdapter
import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.databinding.FragmentSaleBinding
import com.newAdmilaTea.newadmilatea.databinding.FragmentStopBinding
import com.newAdmilaTea.newadmilatea.model.LastModel


class StopFragment : Fragment() {

    lateinit var binding : FragmentStopBinding
    lateinit var stopAdapter : StopAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentStopBinding.inflate(layoutInflater)
        stopAdapter = StopAdapter()

        binding.recuclerStop.adapter = stopAdapter
        binding.recuclerStop.layoutManager = LinearLayoutManager(binding.root.context,
            RecyclerView.VERTICAL,false)
        binding.recuclerStop.setHasFixedSize(true)
        binding.recuclerStop.recycledViewPool.setMaxRecycledViews(100, 100)
        binding.recuclerStop.setItemViewCacheSize(300)
        binding.recuclerStop.isDrawingCacheEnabled = true

        var list : ArrayList<LastModel> = ArrayList()

        var Lasr = LastModel()

        Lasr.name = "Пришло"

        var sda = LastModel()
        sda.name = "Ушло"
        list.add(Lasr)
        list.add(sda)
        updateAdapter(list)
        return binding.root
    }

    private fun updateAdapter(list: ArrayList<LastModel>) {
        stopAdapter.setupStop(list)
    }

    companion object {

        fun newInstance() =
            StopFragment()
    }
}