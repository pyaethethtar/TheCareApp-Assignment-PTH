package com.example.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.delegates.SpecialCaseSummaryDelegate
import com.example.thecareapp.mvp.views.SpecialQuestionsView

interface SpecialQuestionsPresenter : BasePresenter<SpecialQuestionsView>, SpecialCaseSummaryDelegate {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapContinue()
}