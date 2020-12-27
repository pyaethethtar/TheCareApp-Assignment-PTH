package com.example.thecareapp.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.thecareapp.R
import kotlinx.android.synthetic.main.dialog_profile_filling.*

class ProfileFillingDialog : DialogFragment() {

    companion object{
        const val PROFILE_FILLING_DIALOG = "PROFILE_FILLING_DIALOG"
        fun newDialog() : ProfileFillingDialog{
            return ProfileFillingDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_profile_filling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClose.setOnClickListener {
            dismiss()
        }
        btnFill.setOnClickListener {
            dismiss()
        }
    }
}