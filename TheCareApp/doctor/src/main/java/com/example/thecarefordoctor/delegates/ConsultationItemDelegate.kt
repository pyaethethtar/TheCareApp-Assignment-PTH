package com.example.thecarefordoctor.delegates

interface ConsultationItemDelegate {

    fun onTapConsultationHistory(consultationId: String)
    fun onTapPrescription(consultationId: String)
    fun onTapNotes(consultationId: String)
    fun onTapSendMessage(consultationId: String)
}