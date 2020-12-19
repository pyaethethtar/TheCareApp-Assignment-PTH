package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.PatientInfoView

interface PatientInfoPresenter : BasePresenter<PatientInfoView> {

    fun onUiReady(patientId : String, lifecycleOwner: LifecycleOwner)
    fun onTapStartConsultation()
    fun onTapBack()
}