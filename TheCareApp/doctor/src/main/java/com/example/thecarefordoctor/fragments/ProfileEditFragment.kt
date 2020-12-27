package com.example.thecarefordoctor.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.shared.BaseFragment
import com.example.shared.data.vos.DoctorVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.mvp.presenters.ProfileEditPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.ProfileEditPresenterImpl
import com.example.thecarefordoctor.mvp.views.ProfileEditView
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import kotlinx.android.synthetic.main.fragment_profile_edit.etAddress
import kotlinx.android.synthetic.main.fragment_profile_edit.etBrief
import kotlinx.android.synthetic.main.fragment_profile_edit.etCertificates
import kotlinx.android.synthetic.main.fragment_profile_edit.etExperience
import kotlinx.android.synthetic.main.fragment_profile_edit.etName
import kotlinx.android.synthetic.main.fragment_profile_edit.etPhone
import kotlinx.android.synthetic.main.fragment_profile_edit.ivDoctor
import kotlinx.android.synthetic.main.fragment_profile_edit.spinnerDay
import kotlinx.android.synthetic.main.fragment_profile_edit.spinnerMonth
import kotlinx.android.synthetic.main.fragment_profile_edit.spinnerSpeciality
import kotlinx.android.synthetic.main.fragment_profile_edit.spinnerYear
import java.io.IOException

class ProfileEditFragment : BaseFragment(), ProfileEditView {

    private lateinit var mDoctorId: String
    private lateinit var mPresenter : ProfileEditPresenter
    private lateinit var mContext : Context
    private lateinit var mImage : Bitmap


    companion object{
        const val DOCTOR_ID_EXTRA = "DOCTOR_ID_EXTRA"
        const val PICK_IMAGE_REQUEST = 1111
        fun newInstance(doctorId: String): ProfileEditFragment{
            val fragment = ProfileEditFragment()
            val bundle = Bundle()
            bundle.putString(DOCTOR_ID_EXTRA, doctorId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDoctorId = arguments?.getString(DOCTOR_ID_EXTRA)?:"mfw0pfd21nWQgu8o3mWDZKMJFIw1"
        mImage = mContext.resources.getDrawable(R.drawable.photo_1438761681033_6461_ffad_8_d_80).toBitmap()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        mPresenter.onUiReady(mDoctorId, this)
        setUpListener()


    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ProfileEditPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        btnBack.setOnClickListener {
            mPresenter.onTapBack()
        }

        btnUploadImage.setOnClickListener {
            openGallery()
        }

        btnSave.setOnClickListener {
            val doctorVO = DoctorVO()
            doctorVO.doctorId = mDoctorId
            doctorVO.doctorName = etName.text.toString()
            doctorVO.doctorPhone = etPhone.text.toString()
            doctorVO.speciality = spinnerSpeciality.selectedItem.toString()
            doctorVO.doctorDob =  spinnerDay.selectedItem.toString()+"/"+spinnerMonth.selectedItem.toString()+"/"+spinnerYear.selectedItem.toString()
            when(rgGender.checkedRadioButtonId){
                R.id.rbMale -> doctorVO.doctorGender = "Male"
                R.id.rbFemale -> doctorVO.doctorGender = "Female"
            }
            doctorVO.experienceYear = etExperience.text.toString().toInt()
            doctorVO.certificates = etCertificates.text.toString()
            doctorVO.doctorBrief = etBrief.text.toString()
            doctorVO.doctorAddress = etAddress.text.toString()


            mPresenter.onTapSave(doctorVO, mImage)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun displayDoctorInfo(doctorVO: DoctorVO) {
        Glide.with(mContext).load(doctorVO.doctorProfileImage).into(ivDoctor)
        etName.setText(doctorVO.doctorName)
        etPhone.setText(doctorVO.doctorPhone)
        val exp = doctorVO.experienceYear.toString().substringBefore("yrs")
        etExperience.setText(exp)
        etAddress.setText(doctorVO.doctorAddress)
        etCertificates.setText(doctorVO.certificates)
        etBrief.setText(doctorVO.doctorBrief)

        mImage = ivDoctor.drawable.toBitmap()
    }

    override fun navigateToProfileScreen() {
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.flContainer, ProfileFragment.newInstance(mDoctorId)).commit()
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== PICK_IMAGE_REQUEST && resultCode== Activity.RESULT_OK){
            if (data==null || data.data==null){
                return
            }
            val filepath = data.data
            try {
                filepath?.let {
                    if (Build.VERSION.SDK_INT>=29){
                        val source : ImageDecoder.Source = ImageDecoder.createSource(mContext.contentResolver, filepath)
                        mImage = ImageDecoder.decodeBitmap(source)

                    }
                    else{
                        mImage = MediaStore.Images.Media.getBitmap(mContext.contentResolver, filepath)

                    }
                }
            }
            catch (e : IOException){
                e.printStackTrace()
            }
            ivDoctor.setImageBitmap(mImage)
        }
    }
}