package com.example.thecareapp.dialogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.thecareapp.R
import com.example.thecareapp.activities.CaseSummaryActivity
import com.example.thecareapp.activities.ChatActivity
import com.example.thecareapp.mvp.presenters.HomePresenter
import com.example.thecareapp.mvp.presenters.impls.HomePresenterImpl
import kotlinx.android.synthetic.main.dialog_confirm_consultation.*
import kotlinx.android.synthetic.main.dialog_confirm_consultation.view.*

class ConsultationConfirmDialog : DialogFragment() {

    private lateinit var mPresenter : HomePresenter
    private lateinit var mSpeciality : String
    private var mPatientId : String = ""
    private lateinit var mView: View
    private lateinit var mContext: Context

    companion object{
        const val CONSULTATION_CONFIRM_DIALOG = "CONSULTATION_CONFIRM_DIALOG"
        const val BUNDLE_SPECIALITY = "BUNDLE_SPECIALITY"
        const val BUNDLE_PATIENT_ID = "BUNDLE_PATIENT_ID"
        fun newDialog(speciality: String, patientId: String) : ConsultationConfirmDialog{
            val dialog = ConsultationConfirmDialog()
            val bundle = Bundle()
            bundle.putString(BUNDLE_SPECIALITY, speciality)
            bundle.putString(BUNDLE_PATIENT_ID, patientId)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSpeciality = arguments?.getString(BUNDLE_SPECIALITY) ?: ""
        mPatientId = arguments?.getString(BUNDLE_PATIENT_ID) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_confirm_consultation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view
        setUpPresenter()
        setUpListeners()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
    }

    private fun setUpListeners(){
        mView.btnYes.setOnClickListener {
            startActivity(CaseSummaryActivity.newIntent(mPatientId, mSpeciality, mContext))
            dismiss()
        }

        mView.btnNo.setOnClickListener {
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}