package com.example.thecarefordoctor.activities

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
import com.example.shared.data.vos.MedicationVO
import com.example.shared.viewpods.PatientInfoViewpod
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.adapters.ChatMedicineAdapter
import com.example.thecarefordoctor.adapters.ChatMessageAdapter
import com.example.thecarefordoctor.mvp.presenters.ChatPresenter
import com.example.thecarefordoctor.mvp.presenters.impls.ChatPresenterImpl
import com.example.thecarefordoctor.mvp.views.ChatView
import com.example.thecarefordoctor.view.viewpods.ChatPrescriptionViewpod
import kotlinx.android.synthetic.main.activity_chat.*
import java.io.IOException

class ChatActivity : BaseActivity(), ChatView {

    private lateinit var mPresenter : ChatPresenter
    private lateinit var mAdapter: ChatMessageAdapter
    private lateinit var mDoctorId : String
    private lateinit var mConsultationId: String
    private lateinit var mPatientInfoViewpod: PatientInfoViewpod
    private lateinit var mPrescriptionViewpod: ChatPrescriptionViewpod
    private var mDoctorImage: String = ""
    private var mPatientImage : String = ""

    companion object{
        const val PICK_IMAGE_REQUEST = 1111
        const val DOCTOR_ID_EXTRA = "DOCTOR_ID_EXTRA"
        const val CONSULTATION_ID_EXTRA = "CONSULTATION_ID_EXTRA"
        fun newIntent(doctorId: String, consultationId: String, context: Context) : Intent{
            val intent =  Intent(context, ChatActivity::class.java)
            intent.putExtra(DOCTOR_ID_EXTRA, doctorId)
            intent.putExtra(CONSULTATION_ID_EXTRA, consultationId)
            return intent
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mDoctorId = intent.getStringExtra(DOCTOR_ID_EXTRA)?:"mfw0pfd21nWQgu8o3mWDZKMJFIw1"
        mConsultationId = intent.getStringExtra(CONSULTATION_ID_EXTRA)?:"0wBBPLG4o8kyx2xpEHqP"

        setUpPresenter()
        setUpViewpod()
        setUpRecyclerview()
        mPresenter.onUiReady(mConsultationId, mDoctorId, this)
        setUpListener()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ChatPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpViewpod(){
        mPatientInfoViewpod = vpPatientInfo as PatientInfoViewpod
        mPrescriptionViewpod = vpChatPrescription as ChatPrescriptionViewpod
    }

    private fun setUpRecyclerview(){
        mAdapter = ChatMessageAdapter()
        rvChats.adapter = mAdapter
        rvChats.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener(){
        btnSendMessage.setOnClickListener {
            etMessage.text?.let {
                mPresenter.onTapSendMessage(it.toString())
                etMessage.text?.clear()
            }
        }
        btnAttach.setOnClickListener {
            openGallery()
        }
        vpPatientInfo.setOnClickListener {
            mPresenter.onTapCaseSummary()
        }

        btnQuestions.setOnClickListener {
            mPresenter.onTapQuestions()
        }
        btnPrescription.setOnClickListener {
            mPresenter.onTapPrescriptions()
        }
        btnCaseSummary.setOnClickListener {
            mPresenter.onTapPatientInfo()
        }
    }

    override fun displayPatientInfo(consultationVO: ConsultationVO) {
        mDoctorImage = consultationVO.doctorInfo.doctorProfileImage ?: ""
        mPatientImage = consultationVO.patientInfo.patientProfileImage ?: ""

        tvPatientName.text = consultationVO.patientInfo.patientName
        Glide.with(this).load(mPatientImage).circleCrop().into(ivPatient)

        mPatientInfoViewpod.displayGeneralCaseSummary(consultationVO.patientInfo.patientName, consultationVO.caseSummary)
        mPatientInfoViewpod.displaySpecialCaseSummary(consultationVO.caseSummary.subList(0,2).toList())
    }

    override fun navigateToPatientInfoDetailsScreen() {
        startActivity(Intent(this, PatientInfoDetailsActivity::class.java))
    }

    override fun displayMessages(chats: ArrayList<ChatVO>) {
        mAdapter.setNewData(chats)
    }

    override fun displayMedicineRecommendation(medications: ArrayList<MedicationVO>) {
        val medicines = arrayListOf<String>()
        for (medication in medications){
            medicines.add(medication.medicine)
        }
        vpChatPrescription.visibility = View.VISIBLE
        mPrescriptionViewpod.displayMedicines(medicines)
    }

    override fun navigateToCaseSummaryScreen() {
        startActivity(Intent(this, CaseSummaryActivity::class.java))
    }

    override fun navigateToQuestionsScreen() {
        startActivity(QuestionsActivity.newIntent(mConsultationId, this))
    }

    override fun navigateToPrescriptionScreen() {
        startActivity(PrescriptionActivity.newIntent(mConsultationId, this))
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