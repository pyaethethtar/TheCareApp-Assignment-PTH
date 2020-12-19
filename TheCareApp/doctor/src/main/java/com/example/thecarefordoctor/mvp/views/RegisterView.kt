package com.example.thecarefordoctor.mvp.views

import com.example.shared.mvp.BaseView

interface RegisterView : BaseView {

    fun displayPatientImageAndName(name: String, image: String)
    fun navigateToLoginScreen()
}