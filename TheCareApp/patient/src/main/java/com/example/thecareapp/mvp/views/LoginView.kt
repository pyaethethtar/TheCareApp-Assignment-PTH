package com.example.thecareapp.mvp.views

import android.net.Uri
import com.example.shared.mvp.BaseView

interface LoginView : BaseView{

    fun navigateToMainScreen(userId : String)
    fun sendRequestFacebookLogin()
    fun navigateToRegisterScreen(id: String, name: String,email: String, image: String)
}