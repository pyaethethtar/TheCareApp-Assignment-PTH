package com.example.thecareapp.mvp.views

import com.example.shared.mvp.BaseView

interface RegisterPhoneView : BaseView {

    fun requestOTP(phone: String)
    fun navigateToPasswordScreen(id: String, name: String, email: String, image: String, phone: String)
}