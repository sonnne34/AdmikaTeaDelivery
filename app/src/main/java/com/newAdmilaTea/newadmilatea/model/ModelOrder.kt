package com.newAdmilaTea.newadmilatea.model


class ModelOrder(var date1 : String, var money : String, var order : String, var source : String, var status : String, var tel : String) {
    constructor() : this(date1 = String(), money = String(), order = String(), source = String(), status = String(),tel = String())
}