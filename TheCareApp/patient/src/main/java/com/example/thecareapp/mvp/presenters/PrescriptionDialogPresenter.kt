package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.PrescriptionDialogView

interface PrescriptionDialogPresenter  :BasePresenter<PrescriptionDialogView> {

    fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner)
}