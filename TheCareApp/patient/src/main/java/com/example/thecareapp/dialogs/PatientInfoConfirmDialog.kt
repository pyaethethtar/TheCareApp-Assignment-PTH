package com.example.thecareapp.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.thecareapp.R

class PatientInfoConfirmDialog : DialogFragment() {

    companion object{
        const val PATIENT_INFO_CONFIRM_DIALOG = "PATIENT_INFO_CONFIRM_DIALOG"
        fun newDialog()=PatientInfoConfirmDialog().apply {
            return PatientInfoConfirmDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_confirm_patient_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}