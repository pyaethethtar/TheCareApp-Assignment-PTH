package com.example.thecareapp.mvp.presenters.impls

import android.graphics.Bitmap
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.RegisterPhonePresenter
import com.example.thecareapp.mvp.views.RegisterPhoneView

class RegisterPhonePresenterImpl : RegisterPhonePresenter, AbstractBasePresenter<RegisterPhoneView>() {


    override fun onTapGetOTP(phone: String) {
        mView?.requestOTP(phone)
    }

    override fun onTapNext(id: String, name: String,email: String, image: String, phone: String) {
        mView?.navigateToPasswordScreen(id, name, email, image, phone)
    }
}