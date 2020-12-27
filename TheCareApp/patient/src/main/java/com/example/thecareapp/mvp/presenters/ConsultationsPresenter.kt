package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.ConsultationsView

interface ConsultationsPresenter : BasePresenter<ConsultationsView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapPrescription(consultationId: String)
    fun onTapSendMessage(consultationId: String)
}