package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.ConsultationVO
import com.example.shared.mvp.BaseView

interface ConsultationsView : BaseView {

    fun displayConsultations(consulations: List<ConsultationVO>)
    fun displayPrescriptionDialog(consultationId: String)
    fun navigateToChatScreen(consultationId: String, patientId: String)
}