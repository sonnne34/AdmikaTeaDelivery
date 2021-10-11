package com.newAdmilaTea.newadmilatea.authentication

interface AuthenticationInterface {
    interface View{
        fun onSuccess(message : String)
        fun onError(message : String)
    }
    interface Presenter{
        fun doLogin(email : String, password : String)
    }
}