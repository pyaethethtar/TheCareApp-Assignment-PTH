package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.GeneralQuestionVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.mvp.BaseView

interface CaseSummaryView : BaseView {

    fun navigateToGeneralQuestions(patientVO: PatientVO)
    fun navigateToGeneralQuestionsNew()
    fun navigateToSpecialQuestions(questions : ArrayList<SpecialQuestionVO>)
    fun navigateToMainScreen()
    fun displayPatientInfoConfirmatioinDialog(request: ConsultationRequestVO)
}