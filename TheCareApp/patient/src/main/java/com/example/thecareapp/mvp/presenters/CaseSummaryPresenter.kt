package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.CaseSummaryView

interface CaseSummaryPresenter : BasePresenter<CaseSummaryView> {

    fun onUiReady(patientId: String, speciality: String, lifecycleOwner: LifecycleOwner)
}