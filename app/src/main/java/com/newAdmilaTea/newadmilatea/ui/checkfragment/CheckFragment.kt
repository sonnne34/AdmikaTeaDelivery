package com.newAdmilaTea.newadmilatea.ui.checkfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminkatea.adapter.CheckAdapter
import com.newAdmilaTea.newadmilatea.MainActivity
import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.databinding.FragmentCheckBinding
import com.newAdmilaTea.newadmilatea.listener.EventListenerss
import com.newAdmilaTea.newadmilatea.model.MenuModelcatMenu
import com.newAdmilaTea.newadmilatea.singleton.BasketSingleton
import java.util.ArrayList


class CheckFragment : Fragment(), EventListenerss {

  private lateinit var binding : FragmentCheckBinding
  private lateinit var checkAdapter: CheckAdapter
  private var listItem : ArrayList<MenuModelcatMenu> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentCheckBinding.inflate(layoutInflater)
        checkAdapter = CheckAdapter()
        binding.checkRecycler.adapter = checkAdapter
        binding.checkRecycler.layoutManager = LinearLayoutManager(binding.root.context,
            RecyclerView.VERTICAL,false)
        binding.checkRecycler.setHasFixedSize(true)
        binding.checkRecycler.recycledViewPool.setMaxRecycledViews(100, 100)
        binding.checkRecycler.setItemViewCacheSize(300)
        binding.checkRecycler.isDrawingCacheEnabled = true
        BasketSingleton.subscribe(this)

        listItem.addAll(BasketSingleton.basketItem)
        loadItem(listItem)


        binding.buttonNext.setOnClickListener {
            var main = (activity as MainActivity)
            main.toGOcontolCheckFragment()
        }

        return binding.root
    }

    private fun loadItem(list : ArrayList<MenuModelcatMenu>) {
        checkAdapter.setupCheck(list)
    }

    override fun updateRR() {
        var menufile = BasketSingleton.basketItem


        checkAdapter.setupCheck(menufile)
    }


}