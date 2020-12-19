package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.PrescriptionView

interface PrescriptionPresenter : BasePresenter<PrescriptionView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapMedicine(medicine : String)
    fun onTapSearch()
    fun onTapFinish()
}