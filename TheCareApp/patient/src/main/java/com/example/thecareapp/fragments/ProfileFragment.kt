package com.example.thecareapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.shared.BaseFragment
import com.example.shared.data.vos.PatientVO
import com.example.thecareapp.R
import com.example.thecareapp.dialogs.ProfileFillingDialog
import com.example.thecareapp.mvp.presenters.ProfilePresenter
import com.example.thecareapp.mvp.presenters.impls.ProfilePresenterImpl
import com.example.thecareapp.mvp.views.ProfileView
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment(), ProfileView {

    private lateinit var mPatientId: String
    private lateinit var mContext : Context
    private lateinit var mPresenter : ProfilePresenter

    companion object{
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        fun newInstance(patientId: String): ProfileFragment{
            val fragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putString(PATIENT_ID_EXTRA, patientId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPatientId = arguments?.getString(PATIENT_ID_EXTRA)?:""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        mPresenter.onUiReady(mPatientId, this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ProfilePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        val ivEditButton = appbar.findViewById<AppCompatImageView>(R.id.btnEditProfile)
        ivEditButton.setOnClickListener {
            mPresenter.onTapEdit(mPatientId)
        }

        val ivBackButton = appbar.findViewById<AppCompatImageView>(R.id.btnBackToMain)
        ivBackButton.setOnClickListener {
            mPresenter.onTapBack()
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun displayPatientInfo(patientVO: PatientVO) {
        Glide.with(mContext).load(patientVO.patientProfileImage).into(ivPatient)
        tvPatientName.text = patientVO.patientName
        tvPatientPhone.text = patientVO.patientPhone
        tvAddress.text = patientVO.patientAddress

        patientVO.patientCaseSummary?.let {
            for (cs in it){
                when(cs.question) {
                    mContext.getString(R.string.lbl_dob) -> tvDob.text = cs.answer
                    mContext.getString(R.string.lbl_height) -> tvHeight.text = cs.answer
                    mContext.getString(R.string.lbl_blood_type) -> tvBloodType.text = cs.answer
                    mContext.getString(R.string.lbl_allergic_medicine) -> tvAllergicMedicine.text = cs.answer
                }
            }
        }

    }

    override fun navigateToProfileEdit(patientId: String) {
        openFragment(ProfileEditFragment.newInstance(patientId))
    }

    override fun displayEmptyDialog() {
        activity?.let {
            ProfileFillingDialog.newDialog().show(it.supportFragmentManager, ProfileFillingDialog.PROFILE_FILLING_DIALOG)
        }
    }


    private fun openFragment(fragment : Fragment){
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
        }
    }
}