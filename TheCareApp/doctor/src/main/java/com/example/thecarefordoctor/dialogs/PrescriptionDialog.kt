package com.example.thecarefordoctor.dialogs

import androidx.fragment.app.DialogFragment

class PrescriptionDialog : DialogFragment() {

    companion object{
        const val PRESCRIPTION_DIALOG = "PRESCRIPTION_DIALOG"
        fun newDialog() : PrescriptionDialog{
            return PrescriptionDialog()
        }
    }
}