package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.shared.activities.BaseActivity
import com.example.shared.data.vos.ConsultationVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.mvp.presenters.NotePresenter
import com.example.thecarefordoctor.mvp.presenters.impls.NotePresenterImpl
import com.example.thecarefordoctor.mvp.views.NoteView
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : BaseActivity(), NoteView {

    private lateinit var mConsultationId: String
    private lateinit var mPresenter : NotePresenter

    companion object{
        const val CONSULTATION_ID_EXTRA = "CONSULTATION_ID_EXTRA"
        fun newIntent(consultationId: String, context: Context): Intent{
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(CONSULTATION_ID_EXTRA, consultationId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        mConsultationId = intent.getStringExtra(PrescriptionActivity.CONSULTATION_ID_EXTRA)?:"0wBBPLG4o8kyx2xpEHqP"

        setUpPresenter()
        mPresenter.onUiReady(mConsultationId, this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(NotePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        btnSave.setOnClickListener {
            mPresenter.onTapSave(etNote.text.toString())
        }
    }

    override fun displayConsultationInfo(consultationVO: ConsultationVO) {
        tvPatientName.text = consultationVO.patientInfo.patientName
        for (cs in consultationVO.caseSummary){
            if (cs.question==getString(R.string.lbl_dob)){
                tvDob.text = cs.answer
            }
        }
        tvConsultationDate.text = consultationVO.consultationDate

    }

    override fun navigateToChatScreen() {
        this.finish()
    }


}