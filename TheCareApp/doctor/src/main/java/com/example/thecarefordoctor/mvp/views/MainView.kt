package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.BaseView

interface MainView : BaseView {

    fun displayDoctorInfo(doctorVO: DoctorVO)
    fun displayNewRequests(consultationRequests: List<ConsultationRequestVO>)
    fun displayConsultationList(consultations: List<ConsultationVO>)
    fun displayEmptyRequest()
    fun displayEmptyConsultation()
    fun navigateToDoctorProfile(doctorId: String)
    fun navigateToPatientCaseSummary(consultationRequestVO: ConsultationRequestVO)
    fun navigateToNotesScreen(consultationId: String)
    fun displayPostponeDialog()
    fun displayPrescriptionDialog(consultationId: String)
    fun displayConsultationHistoryDialog(consultationId: String)
    fun navigateToChatScreen(consultationId: String)
}