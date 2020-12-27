package com.example.thecarefordoctor.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.shared.BaseFragment
import com.example.shared.data.vos.DoctorVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.activities.MainActivity
import com.example.thecarefordoctor.mvp.presenters.ProfilePresenter
import com.example.thecarefordoctor.mvp.presenters.impls.ProfilePresenterImpl
import com.example.thecarefordoctor.mvp.views.ProfileView
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseFragment(), ProfileView {

    private lateinit var mDoctorId: String
    private lateinit var mContext : Context
    private lateinit var mPresenter : ProfilePresenter

    companion object{
        const val DOCTOR_ID_EXTRA = "DOCTOR_ID_EXTRA"
        fun newInstance(doctorId: String): ProfileFragment{
            val fragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putString(DOCTOR_ID_EXTRA, doctorId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mDoctorId = arguments?.getString(DOCTOR_ID_EXTRA)?:"mfw0pfd21nWQgu8o3mWDZKMJFIw1"

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
        mPresenter.onUiReady(mDoctorId, this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ProfilePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        val ivEditButton = appbar.findViewById<AppCompatImageView>(R.id.btnEdit)
        ivEditButton.setOnClickListener {
            mPresenter.onTapEdit(mDoctorId)
        }

        val ivBackButton = appbar.findViewById<AppCompatImageView>(R.id.btnBack)
        ivBackButton.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    override fun displayDoctorInfo(doctorVO: DoctorVO) {
        Glide.with(mContext).load(doctorVO.doctorProfileImage).into(ivDoctor)
        tvDoctorName.text = doctorVO.doctorName
        tvDoctorPhone.text = doctorVO.doctorPhone
        tvSpeciality.text = doctorVO.speciality
        tvDob.text = doctorVO.doctorDob
        tvExperience.text = doctorVO.experienceYear.toString()+" yrs"
        tvGender.text = doctorVO.doctorGender
        tvAddress.text = doctorVO.doctorAddress
        tvCertificates.text = doctorVO.certificates
        tvBrief.text = doctorVO.doctorBrief
    }

    override fun navigateToProfileEdit(doctorId: String) {
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.flContainer, ProfileEditFragment.newInstance(doctorId)).commit()
        }
    }

    override fun navigateToMainScreen() {
        activity?.finish()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


}