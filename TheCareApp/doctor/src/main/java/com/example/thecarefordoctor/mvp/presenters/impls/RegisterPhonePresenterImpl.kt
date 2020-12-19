package com.example.thecarefordoctor.mvp.presenters.impls

import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.RegisterPhonePresenter
import com.example.thecarefordoctor.mvp.views.RegisterPhoneView

class RegisterPhonePresenterImpl : RegisterPhonePresenter, AbstractBasePresenter<RegisterPhoneView>() {


    override fun onTapGetOTP(phone: String) {
        mView?.requestOTP(phone)
    }

    override fun onTapNext(id: String, name: String, email: String, image: String, phone: String) {
        mView?.navigateToPasswordScreen(id, name, email, image, phone)
    }
}