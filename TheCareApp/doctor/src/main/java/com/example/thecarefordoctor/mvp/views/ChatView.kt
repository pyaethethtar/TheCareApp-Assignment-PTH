package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.*
import com.example.shared.mvp.BaseView
import kotlin.collections.ArrayList

interface ChatView : BaseView {

    fun displayPatientInfo(consultationVO: ConsultationVO)
    fun displayMessages(chats : ArrayList<ChatVO>)
    fun displayMedicineRecommendation(medications : ArrayList<MedicationVO>)
    fun navigateToCaseSummaryScreen()
    fun navigateToQuestionsScreen()
    fun navigateToPrescriptionScreen()
    fun navigateToPatientInfoDetailsScreen()
}