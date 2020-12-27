package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.shared.activities.BaseActivity
import com.example.shared.activities.PatientInfoDetailsActivity
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.dialogs.ConsultationHistoryDialog
import com.example.thecarefordoctor.dialogs.PostponeDialog
import com.example.thecarefordoctor.dialogs.PrescriptionDialog
import com.example.thecarefordoctor.mvp.presenters.MainPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.MainPresenterImpl
import com.example.thecarefordoctor.mvp.views.MainView
import com.example.thecarefordoctor.view.viewpods.ConsultationListViewpod
import com.example.thecarefordoctor.view.viewpods.ConsultationRequestViewpod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    private var mDoctorId : String = ""
    private lateinit var mPresenter : MainPresenter
    private lateinit var mRequestViewpod : ConsultationRequestViewpod
    private lateinit var mConsultatioListViewpod: ConsultationListViewpod


    companion object{
        const val DOCTOR_ID_EXTRA = "DOCTOR_ID_EXTRA"
        fun newIntent(id: String, context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(DOCTOR_ID_EXTRA, id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            return  intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDoctorId = intent.getStringExtra(DOCTOR_ID_EXTRA)?:""
        setUpPresenterAndViewpod()
        setUpLister()
        mPresenter.onUiReady(mDoctorId, this)
        mPresenter.onObserveConsultationRequest(this)
    }

    private fun setUpPresenterAndViewpod(){
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)

        mRequestViewpod = vpConsultationRequest as ConsultationRequestViewpod
        mRequestViewpod.setDelegate(mPresenter)

        mConsultatioListViewpod = vpConsultationList as ConsultationListViewpod
        mConsultatioListViewpod.setDelegate(mPresenter)
    }

    private fun setUpLister(){
        ivDocotor.setOnClickListener {
            mPresenter.onTapProfile()
        }
    }

    override fun displayDoctorInfo(doctorVO: DoctorVO) {
        tvDoctorName.text = doctorVO.doctorName
        Glide.with(this).load(doctorVO.doctorProfileImage).circleCrop().into(ivDocotor)
    }

    override fun displayNewRequests(consultationRequests: List<ConsultationRequestVO>) {
        vpConsultationRequest.visibility = View.VISIBLE
        mRequestViewpod.displayRequests(consultationRequests)
    }

    override fun displayConsultationList(consultations: List<ConsultationVO>) {
        vpConsultationList.visibility = View.VISIBLE
        mConsultatioListViewpod.displayConsultationList(consultations)
    }

    override fun displayEmptyRequest() {
        vpConsultationRequest.visibility = View.GONE
    }

    override fun displayEmptyConsultation() {
        vpConsultationList.visibility = View.GONE
    }

    override fun navigateToDoctorProfile(doctorId: String) {
        startActivity(ProfileActivity.newIntent(doctorId, this))
    }

    override fun navigateToPatientCaseSummary(consultationRequestVO: ConsultationRequestVO) {
        startActivity(Intent(this, PatientInfoDetailsActivity::class.java))
    }

    override fun navigateToNotesScreen(consultationId: String) {
        startActivity(NoteActivity.newIntent(consultationId, this))
    }

    override fun displayPostponeDialog() {
        PostponeDialog.newDialog().show(supportFragmentManager, PostponeDialog.POSTPONE_DIALOG)
    }

    override fun displayPrescriptionDialog(consultationId: String) {
        PrescriptionDialog.newDialog(consultationId).show(supportFragmentManager, PrescriptionDialog.PRESCRIPTION_DIALOG)
    }

    override fun displayConsultationHistoryDialog(consultationId: String) {
        ConsultationHistoryDialog.newDialog(consultationId).show(supportFragmentManager, ConsultationHistoryDialog.CONSULTATION_HISTORY_DIALOG)
    }

    override fun navigateToChatScreen(consultationId: String) {
        startActivity(ChatActivity.newIntent(mDoctorId, consultationId, this))
    }




}
