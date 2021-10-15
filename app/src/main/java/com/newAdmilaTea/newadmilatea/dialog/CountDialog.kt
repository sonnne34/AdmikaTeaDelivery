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
            var txtName = dialog.findViewById(R.id.txt_name) as TextView
            txtName.text = menuFile.Item?.Name

            //отправить товар в стоп
            var btn_stop = dialog.findViewById(R.id.btn_stop_dialog) as Button
            btn_stop.setOnClickListener {

                //Изменение цены не происходит (Switch=0), нужно при отправке данных на сервер:
                menuFile.Item?.Switch = 0
                menuFile.Item?.Stop = 0
                    BasketSingleton.addBasket(menuFile)
                    BasketSingleton.showBasket()
                    BasketSingleton.notifyTwo()

                dialog.cancel()
            }

            //удалить скидку
            val btn_del_cost = dialog.findViewById(R.id.btn_del_cost_dialog) as Button

            btn_del_cost.setOnClickListener {
                //Изменение цены происходит (Switch=1), нужно при отправке данных на сервер:
                menuFile.Item?.Switch = 1
                var delCost = 0
                menuFile.Item?.NewCost = delCost.toDouble()

                BasketSingleton.addBasket(menuFile)
                BasketSingleton.showBasket()
                BasketSingleton.notifyTwo()

                dialog.cancel()
            }


            //сохранить изменение цены
            var btn_ok = dialog.findViewById(R.id.btn_ok_dialog) as Button
            btn_ok.setOnClickListener {

                var text =   edite.text.toString()
                //Изменение цены происходит (Switch=1), нужно при отправке данных на сервер:
                menuFile.Item?.Switch = 1

                if(text.isNotEmpty()) {
                    menuFile.Item?.NewCost = text.toDouble()
                }else{
                    dialog.cancel()
                }

                if (!edite.text.toString().equals("")) {
                    BasketSingleton.addBasket(menuFile)
                    BasketSingleton.showBasket()
                    BasketSingleton.notifyTwo()
                } else {
                    if (file != null) {
                        val position: Int = 0
                        BasketSingleton.delPos(position)
                        BasketSingleton.notifyTwo()
                    }
                }
                dialog.cancel()
            }

            //закрыть окно
            val btn_cancel = dialog.findViewById(R.id.btn_cancel_dialog) as Button

            btn_cancel.setOnClickListener {
                dialog.cancel()
            }
        }
    }
}