package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.BaseView

interface PatientInfoView : BaseView {

    fun displayPatientInfo(info : PatientVO)
    fun displayCaseSummary(caseSummary: List<CaseSummaryVO>)
    fun navigateToChatScreen()
}