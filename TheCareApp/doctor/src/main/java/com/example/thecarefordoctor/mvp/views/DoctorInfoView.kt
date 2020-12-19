package com.example.thecarefordoctor.mvp.views

import com.example.shared.mvp.BaseView

interface DoctorInfoView : BaseView {

    fun navigateToMainScreen(doctorId : String)
    fun navigateToRegisterScreen()
}