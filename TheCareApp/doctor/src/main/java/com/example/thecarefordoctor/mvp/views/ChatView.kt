package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ChatVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.BaseView

interface ChatView : BaseView {

    fun displayPatientInfo(info : PatientVO)
    fun displayCaseSummary(caseSummary: List<CaseSummaryVO>)
    fun displayMessages(chats : List<ChatVO>)
    fun navigateToCaseSummaryScreen()
    fun navigateToQuestionsScreen()
    fun navigateToPrescriptionScreen()
}