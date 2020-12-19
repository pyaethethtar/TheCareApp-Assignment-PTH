package com.example.thecarefordoctor.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.shared.BaseFragment
import com.example.shared.data.vos.DoctorVO
import com.example.shared.utils.EM_RETYPE_PASSWORD
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.activities.DoctorInfoActivity
import com.example.thecarefordoctor.activities.MainActivity
import com.example.thecarefordoctor.mvp.presenters.RegisterPasswordPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.RegisterPasswordPresenterImpl
import com.example.thecarefordoctor.mvp.views.RegisterPasswordView
import com.example.thecarefordoctor.mvp.views.RegisterPhoneView
import kotlinx.android.synthetic.main.fragment_register_password.*

class RegisterPasswordFragment  :BaseFragment(), RegisterPasswordView {

    private lateinit var mContext : Context
    private lateinit var mPresenter : RegisterPasswordPresenter
    private var mDoctorId : String = ""
    private var mDoctorName : String = ""
    private var mDoctorEmail : String = ""
    private var mDoctorImage : String = ""
    private var mDoctorPhone : String = ""

    companion object{
        const val DOCTOR_ID_EXTRA = "DOCTOR_ID_EXTRA"
        const val DOCTOR_NAME_EXTRA = "DOCTOR_NAME_EXTRA"
        const val DOCTOR_EMAIL_EXTRA = "DOCTOR_EMAIL_EXTRA"
        const val DOCTOR_IMAGE_EXTRA = "DOCTOR_IMAGE_EXTRA"
        const val DOCTOR_PHONE_EXTRA = "DOCTOR_PHONE_EXTRA"
        fun newInstance(id: String, name: String, email: String, image: String, phone: String)=RegisterPasswordFragment().apply{
            arguments = Bundle().apply {
                putString(DOCTOR_ID_EXTRA, id)
                putString(DOCTOR_NAME_EXTRA, name)
                putString(DOCTOR_EMAIL_EXTRA, email)
                putString(DOCTOR_IMAGE_EXTRA, image)
                putString(DOCTOR_PHONE_EXTRA, phone)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            mDoctorId = it.getString(DOCTOR_ID_EXTRA) ?: ""
            mDoctorName = it.getString(DOCTOR_NAME_EXTRA) ?: ""
            mDoctorEmail = it.getString(DOCTOR_EMAIL_EXTRA) ?: ""
            mDoctorImage = it.getString(DOCTOR_IMAGE_EXTRA) ?: ""
            mDoctorPhone = it.getString(DOCTOR_PHONE_EXTRA)?:""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpListener()


    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(RegisterPasswordPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){

        btnConfirm.setOnClickListener {
            val password = etPassword?.text?.toString()
            val repassword = etRetypePassword?.text?.toString()

            if (password!=null && repassword!=null && password==repassword){
                mPresenter.onTapConfirm(mDoctorId, mDoctorName, mDoctorEmail, mDoctorImage, mDoctorPhone, password)
            }
            else{
                showErrorMessage(EM_RETYPE_PASSWORD)
            }
        }
    }

    override fun navigateToDoctorInfoScreen(doctorVO: DoctorVO) {
        startActivity(DoctorInfoActivity.newIntent(mContext, doctorVO))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}