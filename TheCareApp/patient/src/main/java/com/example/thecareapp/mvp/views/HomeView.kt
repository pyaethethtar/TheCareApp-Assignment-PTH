package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.SpecialityVO
import com.example.shared.mvp.BaseView

interface HomeView : BaseView {

    fun displaySpecialities(specialities: List<SpecialityVO>)
    fun displayRecentlyConsultedDoctors(doctors : List<DoctorVO>)
    fun displayConsultationResponse(consultationVO: ConsultationVO)
    fun displayEmptyResponse()
    fun displayEmptyRecentDoctor()
    fun displayConfimationDialog(speciality: String)
    fun navigateToCaseSummaryScreen(speciality: String)
    fun navigateToChatScreen()
}