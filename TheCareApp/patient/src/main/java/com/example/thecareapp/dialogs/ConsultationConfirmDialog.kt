package com.example.thecareapp.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.thecareapp.R
import com.example.thecareapp.mvp.presenters.HomePresenter
import com.example.thecareapp.mvp.presenters.impls.HomePresenterImpl
import kotlinx.android.synthetic.main.dialog_confirm_consultation.*

class ConsultationConfirmDialog : DialogFragment() {

    private lateinit var mPresenter : HomePresenter
    private lateinit var mSpeciality : String

    companion object{
        const val CONSULTATION_CONFIRM_DIALOG = "CONSULTATION_CONFIRM_DIALOG"
        const val BUNDLE_SPECIALITY = "BUNDLE_SPECIALITY"
        fun newDialog(speciality: String) : ConsultationConfirmDialog{
            val dialog = ConsultationConfirmDialog()
            val bundle = Bundle()
            bundle.putString(BUNDLE_SPECIALITY, speciality)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSpeciality = arguments?.getString(BUNDLE_SPECIALITY) ?: ""
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

        setUpPresenter()
        setUpListeners()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
    }

    private fun setUpListeners(){
        btnYes.setOnClickListener {
            mPresenter.onTapConfirm(mSpeciality)
            dismiss()
        }

        btnNo.setOnClickListener {
            dismiss()
        }
    }
}