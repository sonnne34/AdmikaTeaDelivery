package com.newAdmilaTea.newadmilatea.ui.reportfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.newAdmilaTea.newadmilatea.databinding.FragmentReportBinding
import com.newAdmilaTea.newadmilatea.model.model1
import com.newAdmilaTea.newadmilatea.singleton.BasketSingleton


class ReportFragment : Fragment() {


   lateinit var binding: FragmentReportBinding
   lateinit var mDataBase: DatabaseReference
   var list2 : ArrayList<model1> = ArrayList()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater)








        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}