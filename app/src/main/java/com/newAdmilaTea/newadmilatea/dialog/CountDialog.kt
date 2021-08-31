package com.newAdmilaTea.newadmilatea.dialog

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.*
import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.model.MenuModelcatMenu
import com.newAdmilaTea.newadmilatea.singleton.BasketSingleton


class CountDialog {
    companion object {
        fun openDialog(context: Context, fileMenu: MenuModelcatMenu) {


            var menuFile = fileMenu
            Log.d("PPPP ", " Menufile = " + menuFile.Items?.Name)

            val dialog = Dialog(context, R.style.CustomDialog)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_item_menu)
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setLayout(
                ListPopupWindow.MATCH_PARENT,
                ListPopupWindow.WRAP_CONTENT
            )
            val file = BasketSingleton.proverkaNaNalichie(fileMenu)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
            var edite = dialog.findViewById(R.id.editetext_dialog) as EditText


            var btn_ok = dialog.findViewById(R.id.btn_ok_dialog) as Button
            btn_ok.setOnClickListener {

                Log.d("GORA","${edite.text.toString()}")

                var text =   edite.text.toString()
                menuFile.Items?.newCost = text.toDouble()

                if (!edite.text.toString().equals("")) {
                    BasketSingleton.addBasket(menuFile)
                    BasketSingleton.showBasket()
                    BasketSingleton.notifyTwo()
                }
                else {
                    if (file != null) {
                        val position: Int = 0
                        BasketSingleton.delPos(position)
                        BasketSingleton.notifyTwo()
                    }
                }
                dialog.cancel()
            }

            var btn_cancel = dialog.findViewById(R.id.btn_cancel_dialog) as Button

            btn_cancel.setOnClickListener {
                dialog.cancel()
            }
        }
    }
}