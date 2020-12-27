package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.MedicationVO
import com.example.shared.mvp.BaseView

interface PrescriptionDialogView : BaseView{

    fun displayPrescriptions(prescriptions : List<MedicationVO>)
}