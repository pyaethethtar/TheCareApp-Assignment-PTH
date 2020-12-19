package com.example.thecarefordoctor.dialogs

import android.content.Context
import android.content.Intent
import androidx.fragment.app.DialogFragment

class PostponeDialog : DialogFragment() {

    companion object{
        const val POSTPONE_DIALOG = "POSTPONE_DIALOG"
        fun newDialog() : PostponeDialog{
            return PostponeDialog()
        }
    }
}