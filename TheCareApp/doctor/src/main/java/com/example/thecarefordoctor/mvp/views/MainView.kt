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
    fun navigateToDoctorProfile(doctorVO: DoctorVO)
    fun navigateToPatientCaseSummary(consultationRequestVO: ConsultationRequestVO)
    fun navigateToNotesScreen(consultationVO: ConsultationVO)
    fun displayPostponeDialog()
    fun displayPrescriptionDialog(consultationVO: ConsultationVO)
    fun displayConsultationHistoryDialog(consultationVO: ConsultationVO)
    fun navigateToChatScreen(consultationVO: ConsultationVO)
}