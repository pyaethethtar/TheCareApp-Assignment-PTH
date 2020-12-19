package com.example.thecarefordoctor.mvp.presenters

import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.DoctorInfoView

interface DoctorInfoPresenter : BasePresenter<DoctorInfoView> {

    fun onTapCreateAccount(doctorVO: DoctorVO)
    fun onTapBack()
}