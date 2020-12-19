package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.SpecialityVO
import com.example.shared.mvp.BaseView

interface MainView : BaseView {

    fun navigateToHomeScreen()
    fun navigateToConsultationScreen()
    fun navigateToProfileScreen()
}