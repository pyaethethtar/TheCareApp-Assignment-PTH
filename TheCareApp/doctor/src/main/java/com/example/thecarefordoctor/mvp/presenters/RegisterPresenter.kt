package com.example.thecarefordoctor.mvp.presenters

import android.graphics.Bitmap
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {

    fun onUiReady(name: String?, image: String?)
    fun onTapLogin()
}