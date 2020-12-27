package com.example.thecareapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.delegates.ConsultationResponseDelegate
import com.example.thecareapp.delegates.RecentDoctorDelegate
import com.example.thecareapp.delegates.SpecialityItemDelegate
import com.example.thecareapp.mvp.views.HomeView

interface HomePresenter : BasePresenter<HomeView>, SpecialityItemDelegate, ConsultationResponseDelegate, RecentDoctorDelegate {

    fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner)
}