package com.example.thecareapp.mvp.presenters

import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.LoginView
import com.facebook.AccessToken

interface LoginPresenter : BasePresenter<LoginView> {

    fun onTapLogin(phone: String, password: String)
    fun onTapSignInWithFacebook()
    fun onSuccessFacebookLogin(token: AccessToken)
}