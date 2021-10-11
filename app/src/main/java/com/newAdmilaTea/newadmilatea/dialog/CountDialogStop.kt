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


class CountDialogStop {
    companion object {
        fun openDialog(context: Context, fileMenu: MenuModelcatMenu) {


            var menuFile = fileMenu


            val dialog = Dialog(context, R.style.CustomDialog)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_item_stop)
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setLayout(
                ListPopupWindow.MATCH_PARENT,
                ListPopupWindow.WRAP_CONTENT
            )
            val file = BasketSingleton.proverkaNaNalichie(fileMenu)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()

            var txtName = dialog.findViewById(R.id.txt_name_stop) as TextView
            txtName.text = menuFile.Item?.Name

            var btn_active = dialog.findViewById(R.id.btn_stop_active_dialog) as Button
            btn_active.setOnClickListener {

                //Изменение цены не происходит (Switch=0), нужно при отправке данных на сервер:
                menuFile.Item?.Switch = 0
                menuFile.Item?.Stop = 1
                    BasketSingleton.addBasket(menuFile)
                    BasketSingleton.showBasket()
                    BasketSingleton.notifyTwo()

                dialog.cancel()
            }

            var btn_cancel = dialog.findViewById(R.id.btn_stop_cancel_dialog) as Button

            btn_cancel.setOnClickListener {
                dialog.cancel()
            }
        }
    }
}