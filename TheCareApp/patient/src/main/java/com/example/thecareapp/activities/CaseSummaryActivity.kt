package com.example.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseActivity
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.thecareapp.R
import com.example.thecareapp.dialogs.PatientInfoConfirmDialog
import com.example.thecareapp.fragments.GeneralQuestionsFragment
import com.example.thecareapp.fragments.GeneralQuestionsNewFragment
import com.example.thecareapp.fragments.SpecialQuestionsFragment
import com.example.thecareapp.mvp.presenters.CaseSummaryPresenter
import com.example.thecareapp.mvp.presenters.impls.CaseSummaryPresenterImpl
import com.example.thecareapp.mvp.views.CaseSummaryView
import kotlinx.android.synthetic.main.activity_case_summary.*
import kotlinx.android.synthetic.main.fragment_general_questions.*

class CaseSummaryActivity : BaseActivity(), CaseSummaryView {

    private lateinit var mSpecaility: String
    private lateinit var mPatientId : String
    private lateinit var mPresenter : CaseSummaryPresenter

    companion object{
        const val SPECIALITY_EXTRA = "SPECIALITY_EXTRA"
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        fun newIntent(id: String, speciality: String, context: Context) : Intent{
            val intent = Intent(context, CaseSummaryActivity::class.java)
            intent.putExtra(SPECIALITY_EXTRA, speciality)
            intent.putExtra(MainActivity.PATIENT_ID_EXTRA, id)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_summary)
        //setSupportActionBar(toolbar)

        mPatientId = intent.getStringExtra(PATIENT_ID_EXTRA)?:""
        mSpecaility = intent.getStringExtra(SPECIALITY_EXTRA) ?: ""
        setUpPresenter()
        mPresenter.onUiReady(mPatientId, mSpecaility, this)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(CaseSummaryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun navigateToGeneralQuestions(patientVO: PatientVO) {
        var name = patientVO.patientName
        var dob = ""
        var height = ""
        var bloodType = ""
        var medicine = ""
        patientVO.patientCaseSummary?.let {
            for (cs in it){
                when(cs.question) {
                    getString(R.string.lbl_dob) -> dob = cs.answer
                    getString(R.string.lbl_height) -> height = cs.answer
                    getString(R.string.lbl_blood_type) -> bloodType = cs.answer
                    getString(R.string.lbl_allergic_medicine) -> medicine = cs.answer
                }
            }
        }
        openFragment(GeneralQuestionsFragment.newInstance(name, dob, height, bloodType, medicine))
    }

    override fun navigateToGeneralQuestionsNew() {
        openFragment(GeneralQuestionsNewFragment.newInstance())
    }

    override fun navigateToSpecialQuestions(questions: ArrayList<SpecialQuestionVO>) {
        val fragment = SpecialQuestionsFragment.newInstance()
        openFragment(fragment)
        fragment.displaySpecialQuestions(questions)
    }

    override fun navigateToMainScreen() {
        startActivity(MainActivity.newIntent(mPatientId, this))
    }

    override fun displayPatientInfoConfirmatioinDialog(request: ConsultationRequestVO) {
        PatientInfoConfirmDialog.newDialog().show(supportFragmentManager, PatientInfoConfirmDialog.PATIENT_INFO_CONFIRM_DIALOG)
    }

    private fun openFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.flQuestionsContainer, fragment).commit()
    }
}