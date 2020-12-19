package com.example.thecarefordoctor.mvp.views

import com.example.shared.BaseFragment
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BaseView

interface RegisterPasswordView  :BaseView {

    fun navigateToDoctorInfoScreen(doctorVO: DoctorVO)
}