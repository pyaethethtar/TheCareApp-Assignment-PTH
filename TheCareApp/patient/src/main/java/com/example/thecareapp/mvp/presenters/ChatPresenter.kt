package com.example.thecareapp.mvp.presenters

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecareapp.mvp.views.ChatView

interface ChatPresenter : BasePresenter<ChatView> {

    fun onUiReady(consultationId : String, patientId: String, lifecycleOwner: LifecycleOwner)
    fun onTapCaseSummary()
    fun onTapSendMessage(message: String)
    fun onTapSendImage(image : Bitmap)
    fun onTapCheckout()
}