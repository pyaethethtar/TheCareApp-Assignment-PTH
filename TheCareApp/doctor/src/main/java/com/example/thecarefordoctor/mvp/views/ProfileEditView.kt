package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BaseView

interface ProfileEditView : BaseView {

    fun displayDoctorInfo(doctorVO: DoctorVO)
    fun navigateToProfileScreen()
}