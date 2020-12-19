package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView> {

    fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner)
    fun onTapHome()
    fun onTapConsultationList()
    fun onTapProfile()
}