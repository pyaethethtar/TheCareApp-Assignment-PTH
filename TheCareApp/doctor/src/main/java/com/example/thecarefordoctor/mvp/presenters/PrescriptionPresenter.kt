package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.MedicationVO
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.delegates.MedicationItemDelegate
import com.example.thecarefordoctor.mvp.views.PrescriptionView

interface PrescriptionPresenter : BasePresenter<PrescriptionView>, MedicationItemDelegate {

    fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner)
    fun onTapSearch()
    fun onTapAdd(medication : MedicationVO)
    fun onTapFinish()
}