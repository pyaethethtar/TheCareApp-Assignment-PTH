package com.example.thecareapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseFragment
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.viewpods.PatientInfoViewpod
import com.example.shared.viewpods.SpecialCaseSummaryViewpod
import com.example.thecareapp.R
import com.example.thecareapp.activities.MainActivity
import com.example.thecareapp.mvp.presenters.PatientInfoConfirmPresenter
import com.example.thecareapp.mvp.presenters.impls.PatientInfoConfirmPresenterImpl
import com.example.thecareapp.mvp.views.PatientInfoConfirmView
import kotlinx.android.synthetic.main.fragment_patient_info_confirm.*

class PatientInfoConfirmFragment: BaseFragment(), PatientInfoConfirmView {

    private lateinit var mPresenter : PatientInfoConfirmPresenter
    private lateinit var mPatientInfoViewpod : PatientInfoViewpod
    private lateinit var mSpecialCaseSummaryViewpod: SpecialCaseSummaryViewpod
    private lateinit var mContext: Context

    companion object{
        fun newInstance(): PatientInfoConfirmFragment{
            return PatientInfoConfirmFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patient_info_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenterAndViewpods()
        mPresenter.onUiReady(this)
        setUpListener()

    }

    private fun setUpPresenterAndViewpods(){
        mPresenter = ViewModelProviders.of(this).get(PatientInfoConfirmPresenterImpl::class.java)
        mPresenter.initPresenter(this)

        mPatientInfoViewpod = vpPatientInfo as PatientInfoViewpod
        mSpecialCaseSummaryViewpod = vpSpecialCaseSummary as SpecialCaseSummaryViewpod
    }

    private fun setUpListener(){
        btnCreateConsultationRequest.setOnClickListener {
            mPresenter.onTapCreateConsultationRequest()
        }
    }

    override fun displayConsultationInfo(consultationRequestVO: ConsultationRequestVO) {
        mPatientInfoViewpod.displayPatientInfo(consultationRequestVO.patientInfo.patientName, consultationRequestVO.caseSummary)
        mSpecialCaseSummaryViewpod.displaySpecialCaseSummary(consultationRequestVO.caseSummary)
    }

    override fun navigateToMainScreen() {
        activity?.finish()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


}