package com.example.thecareapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.shared.activities.BaseActivity
import com.example.shared.activities.PatientInfoDetailsActivity
import com.example.shared.data.vos.ChatVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.MedicationVO
import com.example.shared.viewpods.PatientInfoViewpod
import com.example.thecareapp.R
import com.example.thecareapp.adapters.ChatMessageAdapter
import com.example.thecareapp.mvp.presenters.ChatPresenter
import com.example.thecareapp.mvp.presenters.impls.ChatPresenterImpl
import com.example.thecareapp.mvp.views.ChatView
import com.example.thecareapp.view.viewpods.ChatPrescriptionViewPod
import kotlinx.android.synthetic.main.activity_chat.*
import java.io.IOException

class ChatActivity : BaseActivity(), ChatView {

    private lateinit var mPresenter : ChatPresenter
    private lateinit var mAdapter: ChatMessageAdapter
    private lateinit var mPatientId : String
    private lateinit var mConsultationId: String
    private lateinit var mPatientInfoViewpod: PatientInfoViewpod
    private lateinit var mPrescriptionViewpod: ChatPrescriptionViewPod
    private var mDoctorImage: String = ""
    private var mPatientImage : String = ""

    companion object{
        const val PICK_IMAGE_REQUEST = 1111
        const val PATIENT_ID_EXTRA = "PATIENT_ID_EXTRA"
        const val CONSULTATION_ID_EXTRA = "CONSULTATION_ID_EXTRA"
        fun newIntent(patientId: String, consultationId: String, context: Context) : Intent {
            val intent =  Intent(context, ChatActivity::class.java)
            intent.putExtra(PATIENT_ID_EXTRA, patientId)
            intent.putExtra(CONSULTATION_ID_EXTRA, consultationId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mPatientId = intent.getStringExtra(PATIENT_ID_EXTRA)?:""
        mConsultationId = intent.getStringExtra(CONSULTATION_ID_EXTRA)?:""

        setUpPresenter()
        setUpViewpod()
        setUpRecyclerview()
        mPresenter.onUiReady(mConsultationId, mPatientId, this)
        setUpListener()

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ChatPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpViewpod(){
        mPatientInfoViewpod = vpPatientInfo as PatientInfoViewpod
        mPrescriptionViewpod = vpChatPrescription as ChatPrescriptionViewPod
        mPrescriptionViewpod.setPresenter(mPresenter)
    }

    private fun setUpRecyclerview(){
        mAdapter = ChatMessageAdapter()
        rvChats.adapter = mAdapter
        rvChats.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){
        btnSend.setOnClickListener {
            etMessage.text?.let {
                mPresenter.onTapSendMessage(it.toString())
                etMessage.text?.clear()
            }
        }
        btnAttachment.setOnClickListener {
            openGallery()
        }
        vpPatientInfo.setOnClickListener {
            mPresenter.onTapCaseSummary()
        }
    }

    override fun displayDoctorInfo(doctorVO: DoctorVO) {
        tvDoctorName.text = doctorVO.doctorName
        tvDoctorSpeciality.text = doctorVO.speciality
        Glide.with(this).load(doctorVO.doctorProfileImage).circleCrop().into(ivDoctor)
    }

    override fun displayPatientInfo(consultationVO: ConsultationVO) {
        mDoctorImage = consultationVO.doctorInfo.doctorProfileImage ?: ""
        mPatientImage = consultationVO.patientInfo.patientProfileImage ?: ""
        mPatientInfoViewpod.displayGeneralCaseSummary(consultationVO.patientInfo.patientName, consultationVO.caseSummary)
        mPatientInfoViewpod.displaySpecialCaseSummary(consultationVO.caseSummary)
    }

    override fun displayMessages(chats: List<ChatVO>) {
        mAdapter.setNewData(chats)
        if(chats.last().doctor!=null){
            tvDoctorSendingTime.visibility = View.VISIBLE
            tvDoctorSendingTime.text = chats.last().sendingTime
        }
        else{
            tvPatientSendingTime.visibility = View.VISIBLE
            tvPatientSendingTime.text = chats.last().sendingTime
        }
    }

    override fun displayMedicineRecommendation(medications: ArrayList<MedicationVO>) {
        val medicines = arrayListOf<String>()
        for (medication in medications){
            medicines.add(medication.medicine)
        }
        vpChatPrescription.visibility = View.VISIBLE
        mPrescriptionViewpod.displayMedicines(medicines)
    }

    override fun displayKeyboard() {
        chatKeyboard.visibility = View.VISIBLE
    }

    override fun hideKeyboard() {
        chatKeyboard.visibility = View.GONE
    }

    override fun navigateToCheckoutScreen(checkoutId: String) {
        startActivity(CheckoutActivity.newIntent(checkoutId, this))
    }

    override fun navigateToPatientInfoDetailsScreen(consultationId: String) {
        startActivity(Intent(this, PatientInfoDetailsActivity::class.java))
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
                        val source : ImageDecoder.Source = ImageDecoder.createSource(this.contentResolver, filepath)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                       mPresenter.onTapSendImage(bitmap)

                    }
                    else{
                        val bitmap = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, filepath)
                        mPresenter.onTapSendImage(bitmap)
                    }
                }
            }
            catch (e : IOException){
                e.printStackTrace()
            }
        }
    }
}