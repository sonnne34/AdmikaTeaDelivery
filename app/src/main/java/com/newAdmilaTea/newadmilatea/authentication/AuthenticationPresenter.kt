package com.newAdmilaTea.newadmilatea.authentication

class AuthenticationPresenter(view1 : AuthenticationInterface.View) : AuthenticationInterface.Presenter {



    val view = view1

    override fun doLogin(email: String, password: String) {


        if(email.equals("Nik") && password.equals("PASS")){
            view.onSuccess("Successful")
        }else {
            view.onError("wrong password or email")
        }
    }
}