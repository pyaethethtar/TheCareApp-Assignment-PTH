package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.ConsultationHistoryDialogView

interface ConsultationHistoryDialogPresenter : BasePresenter<ConsultationHistoryDialogView> {

    fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner)
}