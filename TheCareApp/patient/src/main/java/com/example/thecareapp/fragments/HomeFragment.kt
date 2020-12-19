package com.example.thecareapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shared.BaseFragment
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.SpecialityVO
import com.example.thecareapp.R
import com.example.thecareapp.activities.CaseSummaryActivity
import com.example.thecareapp.activities.ChatActivity
import com.example.thecareapp.adapters.SpecialityAdapter
import com.example.thecareapp.dialogs.ConsultationConfirmDialog
import com.example.thecareapp.mvp.presenters.HomePresenter
import com.example.thecareapp.mvp.presenters.impls.HomePresenterImpl
import com.example.thecareapp.mvp.views.HomeView
import com.example.thecareapp.view.viewpods.ConsultationResponseViewpod
import com.example.thecareapp.view.viewpods.RecentDoctorsViewpod
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeView {

    private var mPatientId : String = ""
    private lateinit var mContext : Context
    private lateinit var mPresenter : HomePresenter
    private lateinit var mAdapter : SpecialityAdapter
    private lateinit var mRecentDoctorsViewpod: RecentDoctorsViewpod
    private lateinit var mConsultationResponseViewpod: ConsultationResponseViewpod


    companion object{
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        fun newInstance(id: String)=HomeFragment().apply {
            arguments = Bundle().apply {
                putString(PATIENT_ID_EXTRA, id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPatientId = arguments?.getString(PATIENT_ID_EXTRA) ?: ""
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenterAndViewpod()
        setUpRecyclerview()
        mPresenter.onUiReady(mPatientId, this)
        mPresenter.onObserveConsultationResponse(this)


    }


    private fun setUpPresenterAndViewpod(){
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)

        mRecentDoctorsViewpod = vpRecentDoctors as RecentDoctorsViewpod
        mRecentDoctorsViewpod.setDelegate(mPresenter)

        mConsultationResponseViewpod = vpConsultationResponse as ConsultationResponseViewpod
        mConsultationResponseViewpod.setDelegate(mPresenter)

    }

    private fun setUpRecyclerview(){
        mAdapter = SpecialityAdapter(mPresenter)
        rvSpecialities.adapter = mAdapter
        rvSpecialities.layoutManager = GridLayoutManager(mContext, 2)
    }

    override fun displaySpecialities(specialities: List<SpecialityVO>) {
        mAdapter.setNewData(specialities)
    }

    override fun displayRecentlyConsultedDoctors(doctors: List<DoctorVO>) {
        vpRecentDoctors.visibility = View.VISIBLE
        mRecentDoctorsViewpod.displayRecentDoctors(doctors)
    }

    override fun displayConsultationResponse(consultationVO: ConsultationVO) {
        vpConsultationResponse.visibility = View.VISIBLE
        mConsultationResponseViewpod.displayConsultationResponse(consultationVO)
    }

    override fun displayConfimationDialog(speciality: String) {
        activity?.let {
            ConsultationConfirmDialog.newDialog(speciality).show(it.supportFragmentManager, ConsultationConfirmDialog.CONSULTATION_CONFIRM_DIALOG)
        }
    }

    override fun navigateToCaseSummaryScreen(speciality: String) {
        startActivity(CaseSummaryActivity.newIntent(mPatientId, speciality, mContext))
    }

    override fun navigateToChatScreen() {
        startActivity(ChatActivity.newIntent(mContext))
    }

    override fun displayEmptyRecentDoctor() {
        vpRecentDoctors.visibility = View.GONE
    }

    override fun displayEmptyResponse() {
        vpConsultationResponse.visibility = View.GONE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}