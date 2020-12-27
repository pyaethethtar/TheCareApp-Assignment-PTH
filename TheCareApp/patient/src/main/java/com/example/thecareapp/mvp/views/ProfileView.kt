package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.BaseView

interface ProfileView : BaseView {

    fun displayPatientInfo(patientVO: PatientVO)
    fun displayEmptyDialog()
    fun navigateToProfileEdit(patientId: String)
}