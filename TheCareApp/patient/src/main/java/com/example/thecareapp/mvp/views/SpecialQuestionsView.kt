package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.mvp.BaseView

interface SpecialQuestionsView : BaseView {

    fun displaySpecialQuestions(questions : List<SpecialQuestionVO>)
    fun navigateToPatientInfoConfirmation()
}