package com.example.thecarefordoctor.mvp.presenters

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.ChatView

interface ChatPresenter  :BasePresenter<ChatView> {

    fun onUiReady(consultationId : String, lifecycleOwner: LifecycleOwner)
    fun onTapQuestions()
    fun onTapPrescriptions()
    fun onTapCaseSummary()
    fun onTapSendMessage(message: String)
    fun onTapSendImage(image : Bitmap)
}