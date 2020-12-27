package com.example.thecarefordoctor.mvp.views

import com.example.shared.data.vos.ConsultationVO
import com.example.shared.mvp.BaseView

interface NoteView : BaseView{

    fun displayConsultationInfo(consultationVO: ConsultationVO)
    fun navigateToChatScreen()
}