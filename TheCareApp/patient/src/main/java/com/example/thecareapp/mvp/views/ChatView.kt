package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.*
import com.example.shared.mvp.BaseView

interface ChatView: BaseView {

    fun displayDoctorInfo(doctorVO: DoctorVO)
    fun displayPatientInfo(consultationVO: ConsultationVO)
    fun displayMessages(chats : List<ChatVO>)
    fun displayMedicineRecommendation(medications : ArrayList<MedicationVO>)
    fun displayKeyboard()
    fun hideKeyboard()
    fun navigateToCheckoutScreen(checkoutId: String)
    fun navigateToPatientInfoDetailsScreen(consultationId: String)
}