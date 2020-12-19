package com.example.thecareapp.mvp.presenters

import android.graphics.Bitmap
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.RegisterPasswordView

interface RegisterPasswordPresenter  :BasePresenter<RegisterPasswordView> {

    fun onTapConfirm(id:String, name: String, email: String, image:String, phone:String, password:String)

}