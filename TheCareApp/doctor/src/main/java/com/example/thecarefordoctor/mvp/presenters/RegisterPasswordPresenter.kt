package com.example.thecarefordoctor.mvp.presenters

import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.RegisterPasswordView

interface RegisterPasswordPresenter: BasePresenter<RegisterPasswordView> {

    fun onTapConfirm(id:String, name: String, email: String, image:String, phone:String, password:String)
}