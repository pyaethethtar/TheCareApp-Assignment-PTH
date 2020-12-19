package com.example.thecarefordoctor.mvp.views

import com.example.shared.mvp.BaseView

interface LoginView : BaseView {

    fun navigateToMainScreen(userId : String)
    fun sendRequestFacebookLogin()
    fun navigateToRegisterScreen(id: String, name: String,email: String, image: String)
}