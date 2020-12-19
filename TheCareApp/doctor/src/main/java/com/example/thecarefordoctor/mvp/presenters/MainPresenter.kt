package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.delegates.ConsultationItemDelegate
import com.example.thecarefordoctor.delegates.RequestItemDelegate
import com.example.thecarefordoctor.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>, RequestItemDelegate, ConsultationItemDelegate {

    fun onUiReady(doctorId: String, lifecycleOwner: LifecycleOwner)
    fun onObserveConsultationRequest(lifecycleOwner: LifecycleOwner)
    fun onTapProfile()

}