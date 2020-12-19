package com.example.thecarefordoctor.delegates

import com.example.shared.data.vos.ConsultationRequestVO

interface RequestItemDelegate {

    fun onTapConsultationRequest(requestId: String)
    fun onTapAccept(consultationRequest: ConsultationRequestVO)
    fun onTapPostpone()
    fun onTapLater()
}