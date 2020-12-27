package com.example.thecarefordoctor.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.ChatPresenter
import com.example.thecarefordoctor.mvp.views.ChatView

class ChatPresenterImpl : ChatPresenter, AbstractBasePresenter<ChatView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mLifecycleOwner: LifecycleOwner
    private var mConsultationId : String = ""
    private var mDoctorId : String = ""
    private var mConsultationVO = ConsultationVO()

    override fun onUiReady(
        consultationId: String,
        doctorId: String,
        lifecycleOwner: LifecycleOwner
    ) {
        mConsultationId = consultationId
        mLifecycleOwner = lifecycleOwner
        mDoctorId = doctorId

        requestConsultationAndObserveMessages(consultationId, lifecycleOwner)
        requestChatMessages(consultationId, lifecycleOwner)
        requestMedicationList(consultationId, lifecycleOwner)
    }


    override fun onTapQuestions() {
        mView?.navigateToQuestionsScreen()
    }

    override fun onTapPrescriptions() {
        mView?.navigateToPrescriptionScreen()
    }

    override fun onTapCaseSummary() {
        mView?.navigateToCaseSummaryScreen()
    }

    override fun onTapPatientInfo() {
        mView?.navigateToPatientInfoDetailsScreen()
    }

    override fun onTapSendMessage(message: String) {
        mCareModel.addChatTextMessage(mConsultationId, "doctor", message, onSuccess = {
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapSendImage(image: Bitmap) {
        mCareModel.addChatImageMessage(mConsultationId, "doctor", image, onSuccess = {

        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    private fun requestConsultationAndObserveMessages(consultationId: String, lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mConsultationVO = it
                mCareModel.getChatMessagesAndSaveToDB(mConsultationVO, onSuccess = {}, onFailure = {})
                mCareModel.getMedicationListAndSaveToDB(mConsultationVO, onSuccess = {}, onFailure = {})
                mView?.displayPatientInfo(it)
            }
        })
    }

    private fun requestChatMessages(consultationId: String, lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null && it.chats!=null){
                it.chats?.let {
                    if (it.isNotEmpty())    mView?.displayMessages(it)
                }
            }


        })
    }

    private fun requestMedicationList(consultationId: String, lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null && it.prescriptions!=null){
                it.prescriptions?.let {
                    if (it.isNotEmpty()) mView?.displayMedicineRecommendation(it)
                }
            }
        })
    }
}