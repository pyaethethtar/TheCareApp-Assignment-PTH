package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BaseView

interface ProfileView : BaseView {

    fun displayDoctorInfo(doctorVO: DoctorVO)
    fun navigateToProfileEdit(doctorId: String)
    fun navigateToMainScreen()
}