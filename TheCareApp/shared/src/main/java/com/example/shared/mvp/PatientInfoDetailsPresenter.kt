package com.example.shared.mvp

import androidx.lifecycle.LifecycleOwner

interface PatientInfoDetailsPresenter : BasePresenter<PatientInfoDetailsView> {

    fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner)
    fun onTapBack()
}