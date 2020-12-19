package com.example.thecareapp.mvp.presenters

import android.graphics.Bitmap
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.RegisterPhoneView

interface RegisterPhonePresenter : BasePresenter<RegisterPhoneView> {

    fun onTapGetOTP(phone: String)
    fun onTapNext(id: String, name: String, email: String, image: String, phone: String)
}