package com.newAdmilaTea.newadmilatea.ui.reportfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.newAdmilaTea.newadmilatea.databinding.FragmentReportBinding
import com.newAdmilaTea.newadmilatea.model.CatMenuModel
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

        binding.btn.setOnClickListener {
            mDataBase = FirebaseDatabase.getInstance().getReference("User")


            var name = binding.editeName.text.toString()
            var suname = binding.editeSuname.text.toString()
            var newUser = model1()
            newUser.name1 = name
            newUser.suname1 = suname
            mDataBase.push().setValue(newUser)
        }

        binding.btnLoad.setOnClickListener {

            var myRef = FirebaseDatabase.getInstance().getReference("User")
            myRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (ds in dataSnapshot.children) {
                        val value = ds.getValue(model1::class.java)!!

                        list2.add(value)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("dima", "Failed to read value.", error.toException())
                }
            })

        }

        binding.btnReplays.setOnClickListener {

            var  list = BasketSingleton.basketItem

            for (i in list){
                Log.d("TEXP","${i.CategoryNameENG}")

            }

            var nn = "AEJapanese"
            var ss = "Match"
            mDataBase = FirebaseDatabase.getInstance().getReference("RestaurantsMenu/TeaTemple/"+ nn +"/Items/"+ ss + "/NewCost")






//            mDataBase.ref.setValue(i.Items?.newCost)
            }








        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}