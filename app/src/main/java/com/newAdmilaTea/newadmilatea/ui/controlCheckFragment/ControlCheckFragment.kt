package com.newAdmilaTea.newadmilatea.ui.controlCheckFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkatea.adapter.ControlCheckAdapter
import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.databinding.FragmentCheckBinding
import com.newAdmilaTea.newadmilatea.databinding.FragmentControlCheckBinding
import com.newAdmilaTea.newadmilatea.model.MenuModelcatMenu


class ControlCheckFragment : Fragment() {

    private lateinit var binding : FragmentControlCheckBinding
    lateinit var adapterControlAdapter : ControlCheckAdapter
    var list : ArrayList<MenuModelcatMenu> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentControlCheckBinding.inflate(layoutInflater)
        adapterControlAdapter = ControlCheckAdapter()
        binding.checkControlRecycler.adapter = adapterControlAdapter
        binding.checkControlRecycler.layoutManager = LinearLayoutManager(binding.root.context,
            RecyclerView.VERTICAL,false)
        binding.checkControlRecycler.setHasFixedSize(true)
        binding.checkControlRecycler.recycledViewPool.setMaxRecycledViews(100, 100)
        binding.checkControlRecycler.setItemViewCacheSize(300)
        binding.checkControlRecycler.isDrawingCacheEnabled = true

        var ten = MenuModelcatMenu()
        ten.Items?.Name =  "Hello"
        list.add(ten)
        setupAdapter(list)
        return binding.root
    }

    private fun setupAdapter(list : ArrayList<MenuModelcatMenu>) {
        adapterControlAdapter.setupControlCheck(list)
    }


}