package com.newAdmilaTea.newadmilatea.listsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.databinding.FragmentListsBinding
import com.newAdmilaTea.newadmilatea.databinding.FragmentReportBinding

class listsFragment : Fragment() {

    lateinit var binding: FragmentListsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListsBinding.inflate(layoutInflater)

        val fragmentadapter = MyPagerAdapter(childFragmentManager)
        binding.viewPager.adapter = fragmentadapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            listsFragment()


    }
}