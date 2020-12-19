package com.example.thecareapp.mvp.views

import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.mvp.BaseView

interface GeneralQuestionsNewView : BaseView {

    fun navigateToSpecialQuestioins()
}