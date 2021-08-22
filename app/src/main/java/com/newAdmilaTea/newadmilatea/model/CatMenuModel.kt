package com.newAdmilaTea.newadmilatea.model

import com.example.adminkatea.model.ItemMenu
import java.util.HashMap

class CatMenuModel(var CategoryName : String) {
    constructor(): this(CategoryName = String())

    var isHeader = false
//    var CategoryName: String? = null
    var Items: Map<String, ItemMenu> = HashMap()

}