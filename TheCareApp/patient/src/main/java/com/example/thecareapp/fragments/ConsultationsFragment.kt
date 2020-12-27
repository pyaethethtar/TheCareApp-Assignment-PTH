package com.example.thecareapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.BaseFragment
import com.example.shared.data.vos.ConsultationVO
import com.example.thecareapp.R
import com.example.thecareapp.activities.ChatActivity
import com.example.thecareapp.adapters.ConsultationAdapter
import com.example.thecareapp.dialogs.PrescriptionDialog
import com.example.thecareapp.mvp.presenters.ConsultationsPresenter
import com.example.thecareapp.mvp.presenters.impls.ConsultationsPresenterImpl
import com.example.thecareapp.mvp.views.ConsultationsView
import kotlinx.android.synthetic.main.fragment_consultations.*
import kotlinx.android.synthetic.main.item_consultation.*

class ConsultationsFragment : BaseFragment(), ConsultationsView {

    private lateinit var mContext : Context
    private lateinit var mPresenter : ConsultationsPresenter
    private lateinit var mAdapter : ConsultationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_consultations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerview()
        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ConsultationsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerview(){
        mAdapter = ConsultationAdapter(mPresenter)
        rvConsultations.adapter = mAdapter
        rvConsultations.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun displayConsultations(consulations: List<ConsultationVO>) {
        mAdapter.setNewData(consulations)
    }

    override fun displayPrescriptionDialog(consultationId: String) {
        activity?.let {
            PrescriptionDialog.newDialog(consultationId).show(it.supportFragmentManager, PrescriptionDialog.PRESCRIPTION_DIALOG)
        }
    }

    override fun navigateToChatScreen(consultationId: String, patientId:String) {
        startActivity(ChatActivity.newIntent(patientId, consultationId, mContext))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


}