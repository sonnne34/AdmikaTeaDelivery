package com.newAdmilaTea.newadmilatea.authentication


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.newAdmilaTea.newadmilatea.MainActivity
import com.newAdmilaTea.newadmilatea.R
import com.newAdmilaTea.newadmilatea.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity(), AuthenticationInterface.View {

    lateinit var binding: ActivityAuthenticationBinding
    var email = ""
    var password = ""

    lateinit var presenter : AuthenticationPresenter
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = AuthenticationPresenter(this)

        enter()
        binding.btnEnter.setOnClickListener {
            email = binding.editeTextEmail.text.toString()
            password = binding.editeTextPassword.text.toString()
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                onError("Error")
            }else{
                presenter.doLogin(email,password)

            }
        }
    }

    private fun enter() {
        val pref = getPreferences(Context.MODE_PRIVATE)
        val loadName = pref!!.getString("name", "")
        val loadPassword = pref.getString("password", "")

        if(loadName!!.isEmpty()){


            Toast.makeText(this,"Введите пароль",Toast.LENGTH_LONG).show()
        }else{

            var intent = Intent(AuthenticationActivity@this,MainActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onSuccess(message: String) {

        val pref = getPreferences(Context.MODE_PRIVATE)
        val savePerson = pref!!.edit()
        savePerson.putString("name", email).toString()
        savePerson.apply()
        val savePassword = pref!!.edit()
        savePerson.putString("password", password).toString()
        savePerson.apply()
        savePassword.apply()

       var intent = Intent(AuthenticationActivity@this,MainActivity::class.java)
        startActivity(intent)

    }

    override fun onError(message: String) {
        Toast.makeText(this,"$message", Toast.LENGTH_LONG).show()

    }
}