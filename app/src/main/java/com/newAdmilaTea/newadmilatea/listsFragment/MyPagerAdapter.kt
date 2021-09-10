package com.newAdmilaTea.newadmilatea.listsFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
       return 2
    }

    override fun getItem(position: Int): Fragment {
     return  when(position){
         0-> SaleFragment()
         else -> StopFragment()
     }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "SALE"
            else -> {
                return "Stop"
            }
        }
    }
}