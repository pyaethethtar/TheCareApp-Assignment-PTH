package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.PatientInfoConfirmView

interface PatientInfoConfirmPresenter : BasePresenter<PatientInfoConfirmView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapCreateConsultationRequest()
}