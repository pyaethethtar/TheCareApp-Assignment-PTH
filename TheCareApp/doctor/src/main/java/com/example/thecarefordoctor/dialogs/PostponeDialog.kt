package com.example.thecarefordoctor.dialogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.thecarefordoctor.R

class PostponeDialog : DialogFragment() {

    companion object{
        const val POSTPONE_DIALOG = "POSTPONE_DIALOG"
        fun newDialog() : PostponeDialog{
            return PostponeDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_postpone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}