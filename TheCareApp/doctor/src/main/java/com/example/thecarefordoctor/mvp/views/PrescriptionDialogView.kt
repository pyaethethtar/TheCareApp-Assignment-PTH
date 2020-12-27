package com.example.thecarefordoctor.mvp.views

import androidx.fragment.app.DialogFragment
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.mvp.BaseView

interface PrescriptionDialogView : BaseView {

    fun displayPrescriptions(consultationVO: ConsultationVO)
}