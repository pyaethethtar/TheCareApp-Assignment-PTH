package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.delegates.QuestionItemDelegate
import com.example.thecarefordoctor.mvp.views.QuestionsView

interface QuestionsPresenter : BasePresenter<QuestionsView>, QuestionItemDelegate {

    fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner)
}