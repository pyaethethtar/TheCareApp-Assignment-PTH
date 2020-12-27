package com.example.shared.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.shared.R
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.mvp.PatientInfoDetailsPresenter
import com.example.shared.mvp.PatientInfoDetailsPresenterImpl
import com.example.shared.mvp.PatientInfoDetailsView
import com.example.shared.viewpods.GeneralCaseSummaryViewpod
import com.example.shared.viewpods.SpecialCaseSummaryViewpod
import kotlinx.android.synthetic.main.activity_patient_info_details.*

class PatientInfoDetailsActivity : BaseActivity(), PatientInfoDetailsView {

    private lateinit var mPresenter : PatientInfoDetailsPresenter
    private lateinit var mGeneralCaseSummaryViewpod: GeneralCaseSummaryViewpod
    private lateinit var mSpecialCaseSummaryViewpod: SpecialCaseSummaryViewpod
    private var mConsultationId: String = ""

    companion object{
        const val CONSULTATION_ID_EXTRA = "CONSULTATION_ID_EXTRA"
        fun newIntent(consultationId: String, context: Context): Intent{
            val intent = Intent(context, PatientInfoDetailsActivity::class.java)
            intent.putExtra(CONSULTATION_ID_EXTRA, consultationId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info_details)

        mConsultationId = intent.getStringExtra(CONSULTATION_ID_EXTRA)?:""
        setUpPresenterAndViewpods()
        mPresenter.onUiReady(mConsultationId, this)
    }

    private fun setUpPresenterAndViewpods(){
        mPresenter = ViewModelProviders.of(this).get(PatientInfoDetailsPresenterImpl::class.java)
        mPresenter.initPresenter(this)

        mGeneralCaseSummaryViewpod = vpGeneralCasesummary as GeneralCaseSummaryViewpod
        mSpecialCaseSummaryViewpod = vpSpecialCaseSummary as SpecialCaseSummaryViewpod
    }

    override fun displayPatientInfo(name: String, casesummary: List<CaseSummaryVO>) {
        mGeneralCaseSummaryViewpod.displayPatientInfo(name, casesummary)
        mSpecialCaseSummaryViewpod.displaySpecialCaseSummary(casesummary)
    }

    override fun backToChatScreen() {
        this.finish()
    }


}