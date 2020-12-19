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
import kotlinx.android.synthetic.main.fragment_general_questions_new.*

class GeneralQuestionsNewFragment : BaseFragment() {

    private lateinit var mPresenter : CaseSummaryPresenter

    companion object{
        fun newInstance(): GeneralQuestionsNewFragment{
            return GeneralQuestionsNewFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general_questions_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        btnNext.setOnClickListener {
            val caseSummary = arrayListOf<CaseSummaryVO>()

            val dob = spinnerDay.selectedItem.toString()+"/"+spinnerMonth.selectedItem.toString()+"/"+spinnerYear.selectedItem.toString()
            val csDob = CaseSummaryVO(lblDob.text.toString(), dob)
            caseSummary.add(csDob)
            val csHeight = CaseSummaryVO(lblHeight.text.toString(), etHeight.text.toString())
            caseSummary.add(csHeight)
            val csBlood = CaseSummaryVO(lblBloodType.text.toString(), spinnerBlood.selectedItem.toString())
            caseSummary.add(csBlood)
            val csMedicine = CaseSummaryVO(lblAllergicMedicine.text.toString(), etAllergicMedicine.text.toString())
            caseSummary.add(csMedicine)
            val csWeight = CaseSummaryVO(lblWeight.text.toString(), etWeight.text.toString())
            caseSummary.add(csWeight)
            val pressure = etBloodPressure?.text?.toString() ?: "-"
            val csPressure = CaseSummaryVO(lblBloodPressure.text.toString(), pressure)
            caseSummary.add(csPressure)

            mPresenter.onTapNext(caseSummary)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(CaseSummaryPresenterImpl::class.java)
    }
}