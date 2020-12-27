package com.example.thecarefordoctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.shared.activities.BaseActivity
import com.example.shared.data.vos.DoctorVO
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.mvp.presenters.DoctorInfoPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.DoctorInfoPresenterImpl
import com.example.thecarefordoctor.mvp.views.DoctorInfoView
import kotlinx.android.synthetic.main.activity_doctor_info.*

class DoctorInfoActivity : BaseActivity(), DoctorInfoView {

//    private val DOCTOR_ID : String = "DOCTOR_ID"
//    private val PREFERENCE_KEY : String = "PREFERENCE_KEY"
    private lateinit var mPresenter : DoctorInfoPresenter
    private var mDoctorId : String = ""
    private var mDoctorName : String = ""
    private var mDoctorEmail : String = ""
    private var mDoctorImage : String = ""
    private var mDoctorPhone : String = ""
    private var mDoctorPassword: String = ""

    companion object{
        const val DOCTOR_BUNDLE_EXTRA = "DOCTOR_BUNDLE_EXTRA"
        const val DOCTOR_ID_EXTRA = "DOCTOR_ID_EXTRA"
        const val DOCTOR_NAME_EXTRA = "DOCTOR_NAME_EXTRA"
        const val DOCTOR_EMAIL_EXTRA = "DOCTOR_EMAIL_EXTRA"
        const val DOCTOR_IMAGE_EXTRA = "DOCTOR_IMAGE_EXTRA"
        const val DOCTOR_PHONE_EXTRA = "DOCTOR_PHONE_EXTRA"
        const val DOCTOR_PASSWORD_EXTRA = "DOCTOR_PASSWORD_EXTRA"
        fun newIntent(context: Context, doctorVO: DoctorVO): Intent {
            val intent = Intent(context, DoctorInfoActivity::class.java)
            val bundle = Bundle()
            bundle.putString(DOCTOR_ID_EXTRA, doctorVO.doctorId)
            bundle.putString(DOCTOR_NAME_EXTRA, doctorVO.doctorName)
            bundle.putString(DOCTOR_EMAIL_EXTRA, doctorVO.doctorEmail)
            bundle.putString(DOCTOR_IMAGE_EXTRA, doctorVO.doctorProfileImage)
            bundle.putString(DOCTOR_PHONE_EXTRA, doctorVO.doctorPhone)
            bundle.putString(DOCTOR_PASSWORD_EXTRA, doctorVO.doctorPassword)
            intent.putExtra(DOCTOR_BUNDLE_EXTRA, bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_info)
        //setSupportActionBar(toolbar)


        val bundle = intent.getBundleExtra(DOCTOR_BUNDLE_EXTRA)
        bundle?.let {
            mDoctorId = it.getString(DOCTOR_ID_EXTRA) ?: ""
            mDoctorName = it.getString(DOCTOR_NAME_EXTRA) ?: ""
            mDoctorEmail = it.getString(DOCTOR_EMAIL_EXTRA) ?: ""
            mDoctorImage = it.getString(DOCTOR_IMAGE_EXTRA) ?: ""
            mDoctorPhone = it.getString(DOCTOR_PHONE_EXTRA) ?: ""
            mDoctorPassword = it.getString(DOCTOR_PASSWORD_EXTRA) ?: ""
        }

        setUpPresenter()
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter =  ViewModelProviders.of(this).get(DoctorInfoPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){

        btnCreateAccount.setOnClickListener {
            val doctorVO = DoctorVO()
            doctorVO.doctorId = mDoctorId
            doctorVO.doctorName = etName.text.toString()
            doctorVO.doctorEmail = mDoctorEmail
            doctorVO.doctorPassword = mDoctorPassword
            doctorVO.doctorPhone = etPhone.text.toString()
            doctorVO.doctorProfileImage = mDoctorImage
            doctorVO.speciality = spinnerSpeciality.selectedItem.toString()
            doctorVO.doctorDob =  spinnerDay.selectedItem.toString()+"/"+spinnerMonth.selectedItem.toString()+"/"+spinnerYear.selectedItem.toString()
            when(rdGender.checkedRadioButtonId){
                R.id.rbMale -> doctorVO.doctorGender = "Male"
                R.id.rbFemale -> doctorVO.doctorGender = "Female"
            }
            doctorVO.experienceYear = etExperience.text.toString().toInt()
            doctorVO.certificates = etCertificates.text.toString()
            doctorVO.doctorBrief = etBrief.text.toString()
            doctorVO.doctorAddress = etAddress.text.toString()

            mPresenter.onTapCreateAccount(doctorVO)
        }
    }

    override fun navigateToMainScreen(doctorId : String) {
        startActivity(MainActivity.newIntent(doctorId, this))
    }

    override fun navigateToRegisterScreen() {
        Log.d("TheCareForDoctor", "navigateToRegisterScreen")
    }


}


