package com.example.thecarefordoctor.mvp.presenters

import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.RegisterPhoneView

interface RegisterPhonePresenter  :BasePresenter<RegisterPhoneView> {

    fun onTapGetOTP(phone: String)
    fun onTapNext(id: String, name: String, email: String, image: String, phone: String)
}