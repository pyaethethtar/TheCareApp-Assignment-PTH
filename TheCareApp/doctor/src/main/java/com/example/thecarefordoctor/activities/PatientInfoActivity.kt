package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseActivity
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.mvp.presenters.PatientInfoPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.PatientInfoPresenterImpl
import com.example.thecarefordoctor.mvp.views.PatientInfoView

class PatientInfoActivity : BaseActivity(), PatientInfoView {

    private lateinit var mPresenter : PatientInfoPresenter

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, PatientInfoActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        setUpPresenter()

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PatientInfoPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun displayPatientInfo(info: PatientVO) {
        TODO("Not yet implemented")
    }

    override fun displayCaseSummary(caseSummary: List<CaseSummaryVO>) {
        TODO("Not yet implemented")
    }

    override fun navigateToChatScreen() {
        TODO("Not yet implemented")
    }
}