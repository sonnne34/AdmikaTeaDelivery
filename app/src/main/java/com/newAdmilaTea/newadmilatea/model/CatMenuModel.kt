package com.newAdmilaTea.newadmilatea.model

import com.example.adminkatea.model.ItemMenu
import java.util.HashMap

class CatMenuModel(var CategoryName : String, var CategoryNameENG: String) {
    constructor(): this(CategoryName = String(),CategoryNameENG = String())

    var isHeader = false
//    var CategoryName: String? = null
    var Items: HashMap<String, ItemMenu> = HashMap()

}