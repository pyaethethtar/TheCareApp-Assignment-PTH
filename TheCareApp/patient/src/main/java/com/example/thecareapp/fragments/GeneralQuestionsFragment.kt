package com.example.thecareapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseFragment
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.thecareapp.R
import com.example.thecareapp.mvp.presenters.CaseSummaryPresenter
import com.example.thecareapp.mvp.presenters.GeneralQuestionsPresenter
import com.example.thecareapp.mvp.presenters.impls.CaseSummaryPresenterImpl
import com.example.thecareapp.mvp.presenters.impls.GeneralQuestionsPresenterImpl
import com.example.thecareapp.mvp.views.GeneralQuestionsView
import kotlinx.android.synthetic.main.fragment_general_questions.*

class GeneralQuestionsFragment : BaseFragment(), GeneralQuestionsView {

    private lateinit var mPresenter : GeneralQuestionsPresenter
    val consultationRequestVO = ConsultationRequestVO()

    companion object{
        const val PATIENT_NAME_EXTRA = "PATIENT_NAME_EXTRA"
        const val PATIENT_DOB_EXTRA = "PATIENT_DOB_EXTRA"
        const val PATIENT_HEIGHT_EXTRA = "PATIENT_HEIGHT_EXTRA"
        const val PATIENT_BLOOD_TYPE_EXTRA = "PATIENT_BLOOD_TYPE_EXTRA"
        const val PATIENT_ALLERGIC_MEDICINE_EXTRA = "PATIENT_ALLERGIC_MEDICINE_EXTRA"
        fun newInstance(name: String, dob: String, height: String, bloodType: String, allergicMedicine: String)=GeneralQuestionsFragment().apply {
            arguments = Bundle().apply {
                putString(PATIENT_NAME_EXTRA, name)
                putString(PATIENT_DOB_EXTRA, dob)
                putString(PATIENT_HEIGHT_EXTRA, height)
                putString(PATIENT_BLOOD_TYPE_EXTRA, bloodType)
                putString(PATIENT_ALLERGIC_MEDICINE_EXTRA, allergicMedicine)
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


        setUpPresenter()
        mPresenter.onUiReady()
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(GeneralQuestionsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
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

    private fun openFragment(fragment : Fragment){
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.flQuestionsContainer, fragment).commit()
        }
    }

    override fun displayPatientInfo() {
        tvPatientName.setText(arguments?.getString(PATIENT_NAME_EXTRA)?:"-")
        tvDob.setText(arguments?.getString(PATIENT_DOB_EXTRA)?:"-")
        tvHeight.setText(arguments?.getString(PATIENT_HEIGHT_EXTRA)?:"-")
        tvBloodType.setText(arguments?.getString(PATIENT_BLOOD_TYPE_EXTRA)?:"-")
        tvAllergicMedicine.setText(arguments?.getString(PATIENT_ALLERGIC_MEDICINE_EXTRA)?:"-")
    }

    override fun navigateToSpecialQuestioins() {
        openFragment(SpecialQuestionsFragment.newInstance())
    }



}