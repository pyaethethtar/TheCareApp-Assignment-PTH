package com.example.thecareapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseFragment
import com.example.shared.data.vos.CaseSummaryVO
import com.example.thecareapp.R
import com.example.thecareapp.mvp.presenters.CaseSummaryPresenter
import com.example.thecareapp.mvp.presenters.impls.CaseSummaryPresenterImpl
import kotlinx.android.synthetic.main.fragment_general_questions.*

class GeneralQuestionsFragment : BaseFragment() {

    private lateinit var mPresenter : CaseSummaryPresenter

    companion object{
        const val PATIENT_NAME_EXTRA = "PATIENT_NAME_EXTRA"
        const val PATIENT_DOB_EXTRA = "PATIENT_DOB_EXTRA"
        const val PATIENT_HEIGHT_EXTRA = "PATIENT_HEIGHT_EXTRA"
        const val PATIENT_BLOOD_TYPE_EXTRA = "PATIENT_BLOOD_TYPE_EXTRA"
        const val PATIENT_ALLERGIC_MEDICINE_EXTRA = "PATIENT_ALLERGIC_MEDICINE_EXTRA"
        fun newInstance(name: String, dob: String, height: String, bloodType: String, allergicMedicine: String)=GeneralQuestionsFragment().apply {
            arguments = Bundle().apply {
                putString(RegisterPasswordFragment.PATIENT_ID_EXTRA, name)
                putString(RegisterPasswordFragment.PATIENT_NAME_EXTRA, dob)
                putString(RegisterPasswordFragment.PATIENT_EMAIL_EXTRA, height)
                putString(RegisterPasswordFragment.PATIENT_IMAGE_EXTRA, bloodType)
                putString(RegisterPasswordFragment.PATIENT_PHONE_EXTRA, allergicMedicine)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvPatientName.setText(arguments?.getString(PATIENT_NAME_EXTRA)?:"-")
        tvDob.setText(arguments?.getString(PATIENT_DOB_EXTRA)?:"-")
        lblHeight.setText(arguments?.getString(PATIENT_HEIGHT_EXTRA)?:"-")
        lblBloodType.setText(arguments?.getString(PATIENT_BLOOD_TYPE_EXTRA)?:"-")
        lblAllergicMedicine.setText(arguments?.getString(PATIENT_ALLERGIC_MEDICINE_EXTRA)?:"-")

        setUpPresenter()
        btnNext.setOnClickListener {
            val caseSummary = arrayListOf<CaseSummaryVO>()

            val csDob = CaseSummaryVO(lblDob.text.toString(), tvDob.text.toString())
            caseSummary.add(csDob)
            val csHeight = CaseSummaryVO(lblHeight.text.toString(), lblHeight.text.toString())
            caseSummary.add(csHeight)
            val csBlood = CaseSummaryVO(lblBloodType.text.toString(), lblBloodType.text.toString())
            caseSummary.add(csBlood)
            val csMedicine = CaseSummaryVO(lblAllergicMedicine.text.toString(), lblAllergicMedicine.text.toString())
            caseSummary.add(csMedicine)
            val csWeight = CaseSummaryVO(lblWeight.text.toString(), etWeight.text.toString())
            caseSummary.add(csWeight)
            val csPressure = CaseSummaryVO(lblBloodPressure.text.toString(), etBloodPressure.text.toString())
            caseSummary.add(csPressure)

            mPresenter.onTapNext(caseSummary)
        }

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(CaseSummaryPresenterImpl::class.java)
    }
}