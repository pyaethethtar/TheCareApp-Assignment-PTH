package com.example.thecarefordoctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.BasePresenter
import com.example.thecarefordoctor.mvp.views.NoteView

interface NotePresenter : BasePresenter<NoteView> {

    fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner)
    fun onTapSave(note: String)
}