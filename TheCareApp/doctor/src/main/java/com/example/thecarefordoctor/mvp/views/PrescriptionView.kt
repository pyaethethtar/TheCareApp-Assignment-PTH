package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.MedicationVO
import com.example.shared.mvp.BaseView

interface PrescriptionView : BaseView {

    fun displayMedicines(medicines : List<MedicationVO>)
    fun displayMedicationDialog(medicine : String, price: Int)
    fun navigateToChatScreen()
}