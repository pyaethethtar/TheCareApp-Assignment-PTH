package com.example.thecareapp.mvp.presenters

import android.graphics.Bitmap
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {

    fun onUiReady(name: String?, image: String?)
    fun onTapLogin()

}