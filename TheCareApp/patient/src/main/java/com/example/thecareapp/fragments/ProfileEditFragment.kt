package com.example.thecareapp.fragments

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
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO
import com.example.thecareapp.R
import com.example.thecareapp.mvp.presenters.ProfileEditPresenter
import com.example.thecareapp.mvp.presenters.ProfilePresenter
import com.example.thecareapp.mvp.presenters.impls.ProfileEditPresenterImpl
import com.example.thecareapp.mvp.views.ProfileEditView
import kotlinx.android.synthetic.main.fragment_general_questions_new.*
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import kotlinx.android.synthetic.main.fragment_profile_edit.etAllergicMedicine
import kotlinx.android.synthetic.main.fragment_profile_edit.etHeight
import kotlinx.android.synthetic.main.fragment_profile_edit.lblAllergicMedicine
import kotlinx.android.synthetic.main.fragment_profile_edit.lblBloodType
import kotlinx.android.synthetic.main.fragment_profile_edit.lblDob
import kotlinx.android.synthetic.main.fragment_profile_edit.lblHeight
import kotlinx.android.synthetic.main.fragment_profile_edit.spinnerDay
import kotlinx.android.synthetic.main.fragment_profile_edit.spinnerMonth
import kotlinx.android.synthetic.main.fragment_profile_edit.spinnerYear
import java.io.IOException

class ProfileEditFragment  :BaseFragment(), ProfileEditView {

    private lateinit var mPatientId: String
    private lateinit var mContext : Context
    private lateinit var mPresenter : ProfileEditPresenter
    private var mImage : Bitmap ?= null

    companion object{
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        const val PICK_IMAGE_REQUEST = 1111
        fun newInstance(patientId: String): ProfileEditFragment{
            val fragment = ProfileEditFragment()
            val bundle = Bundle()
            bundle.putString(PATIENT_ID_EXTRA, patientId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPatientId = arguments?.getString(PATIENT_ID_EXTRA)?:""
        //mImage = mContext.resources.getDrawable(R.drawable.photo_1438761681033_6461_ffad_8_d_80).toBitmap()
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
        mPresenter.onUiReady(mPatientId, this)
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
            val patientVO = PatientVO()
            patientVO.patientId = mPatientId
            patientVO.patientName = etName.text.toString()
            patientVO.patientPhone = etPhone.text.toString()
            patientVO.patientAddress = etAddress.text.toString()

            val caseSummary = arrayListOf<CaseSummaryVO>()

            val dob = spinnerDay.selectedItem.toString()+"/"+spinnerMonth.selectedItem.toString()+"/"+spinnerYear.selectedItem.toString()
            val csDob = CaseSummaryVO(lblDob.text.toString(), dob)
            caseSummary.add(csDob)

            var blood = "A"
            when(rgBloodTypes.checkedRadioButtonId){
                R.id.rbA -> blood = "A"
                R.id.rbB -> blood = "B"
                R.id.rbAB -> blood = "AB"
                R.id.rbO -> blood = "O"
            }
            val csBlood = CaseSummaryVO(lblBloodType.text.toString(), blood)
            caseSummary.add(csBlood)

            val csHeight = CaseSummaryVO(lblHeight.text.toString(), etHeight.text.toString())
            caseSummary.add(csHeight)

            val csMedicine = CaseSummaryVO(lblAllergicMedicine.text.toString(), etAllergicMedicine.text.toString())
            caseSummary.add(csMedicine)
            patientVO.patientCaseSummary = caseSummary

            mImage?.let {
                mPresenter.onTapSave(patientVO, it)
            }

        }
    }

    override fun displayPatientInfo(patientVO: PatientVO) {
        Glide.with(mContext).load(patientVO.patientProfileImage).into(ivPatient)
        etName.setText(patientVO.patientName)
        etPhone.setText(patientVO.patientPhone)

        mImage = ivPatient?.drawable?.toBitmap()
    }

    override fun navigateToProfileScreen() {
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.flContainer, ProfileFragment.newInstance(mPatientId)).commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
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
            ivPatient.setImageBitmap(mImage)
        }
    }
}