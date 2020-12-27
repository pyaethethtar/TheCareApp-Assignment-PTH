package com.example.thecarefordoctor.mvp.views

import com.example.shared.mvp.BaseView

interface QuestionsView : BaseView {

    fun displayQuestions(questions: List<String>)
    fun navigateToChatScreen(question: String)
}