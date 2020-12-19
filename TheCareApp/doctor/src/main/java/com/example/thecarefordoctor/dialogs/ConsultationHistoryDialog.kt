package com.example.thecarefordoctor.dialogs

import androidx.fragment.app.DialogFragment

class ConsultationHistoryDialog : DialogFragment() {

    companion object{
        const val CONSULTATION_HISTORY_DIALOG = "CONSULTATION_HISTORY_DIALOG"
        fun newDialog() : ConsultationHistoryDialog{
            return ConsultationHistoryDialog()
        }
    }
}