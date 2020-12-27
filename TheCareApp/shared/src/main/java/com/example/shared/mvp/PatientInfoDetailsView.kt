package com.example.shared.mvp

import com.example.shared.data.vos.CaseSummaryVO

interface PatientInfoDetailsView : BaseView {

    fun displayPatientInfo(name: String, casesummary: List<CaseSummaryVO>)
    fun backToChatScreen()
}