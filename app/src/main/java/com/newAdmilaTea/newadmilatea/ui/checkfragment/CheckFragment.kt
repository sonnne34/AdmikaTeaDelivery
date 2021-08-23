package com.newAdmilaTea.newadmilatea.ui.checkfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newAdmilaTea.newadmilatea.R


class CheckFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_check, container, false)
        // Inflate the layout for this fragment
        return root
    }


}