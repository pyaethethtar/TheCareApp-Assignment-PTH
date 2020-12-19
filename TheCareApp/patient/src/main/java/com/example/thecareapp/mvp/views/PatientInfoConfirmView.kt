package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.mvp.BaseView

interface PatientInfoConfirmView : BaseView {

    fun displayConsultationInfo(consultationRequestVO: ConsultationRequestVO)
    fun navigateToMainScreen()
}