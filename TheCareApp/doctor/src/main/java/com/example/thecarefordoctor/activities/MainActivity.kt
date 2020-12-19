package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.shared.BaseActivity
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.utils.DOCTOR_KEY
import com.example.shared.utils.LOGIN_KEY
import com.example.shared.utils.PATIENT_KEY
import com.example.shared.utils.PREFERENCE_FILE_KEY
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.dialogs.ConsultationHistoryDialog
import com.example.thecarefordoctor.dialogs.PostponeDialog
import com.example.thecarefordoctor.dialogs.PrescriptionDialog
import com.example.thecarefordoctor.mvp.presenters.LoginPresenter
import com.example.thecarefordoctor.mvp.presenters.MainPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.LoginPresenterImpl
import com.example.thecarefordoctor.mvp.presenters.impls.MainPresenterImpl
import com.example.thecarefordoctor.mvp.views.LoginView
import com.example.thecarefordoctor.mvp.views.MainView
import com.example.thecarefordoctor.view.viewpods.ConsultationListViewpod
import com.example.thecarefordoctor.view.viewpods.ConsultationRequestViewpod
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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
        mRequestViewpod.displayRequests(consultationRequests)
    }

    override fun displayConsultationList(consultations: List<ConsultationVO>) {
        mConsultatioListViewpod.displayConsultationList(consultations)
    }

    override fun displayEmptyRequest() {
        vpConsultationRequest.visibility = View.GONE
    }

    override fun displayEmptyConsultation() {
        vpConsultationList.visibility = View.GONE
    }

    override fun navigateToDoctorProfile(doctorVO: DoctorVO) {
        startActivity(ProfileActivity.newIntent(this))
    }

    override fun navigateToPatientCaseSummary(consultationRequestVO: ConsultationRequestVO) {
        startActivity(PatientInfoActivity.newIntent(this))
    }

    override fun navigateToNotesScreen(consultationVO: ConsultationVO) {
        startActivity(NoteActivity.newIntent(this))
    }

    override fun displayPostponeDialog() {
        PostponeDialog.newDialog().show(supportFragmentManager, PostponeDialog.POSTPONE_DIALOG)
    }

    override fun displayPrescriptionDialog(consultationVO: ConsultationVO) {
        PrescriptionDialog.newDialog().show(supportFragmentManager, PrescriptionDialog.PRESCRIPTION_DIALOG)
    }

    override fun displayConsultationHistoryDialog(consultationVO: ConsultationVO) {
        ConsultationHistoryDialog.newDialog().show(supportFragmentManager, ConsultationHistoryDialog.CONSULTATION_HISTORY_DIALOG)
    }

    override fun navigateToChatScreen(consultationVO: ConsultationVO) {
        startActivity(ChatActivity.newIntent(this))
    }




}
