package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.mvp.BaseView

interface GeneralQuestionsView : BaseView {

    fun displayPatientInfo()
    fun navigateToSpecialQuestioins()
}