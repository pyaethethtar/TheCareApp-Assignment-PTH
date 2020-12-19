package com.example.thecarefordoctor.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.ChatPresenter
import com.example.thecarefordoctor.mvp.views.ChatView

class ChatPresenterImpl : ChatPresenter, AbstractBasePresenter<ChatView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private var mPatientInfo = PatientVO()
    private var mCaseSummary = arrayListOf<CaseSummaryVO>()
    private var mConsultationId : String = ""
    private lateinit var mLifecycleOwner: LifecycleOwner

    override fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner) {
        mConsultationId = consultationId
        mLifecycleOwner = lifecycleOwner

        mCareModel.getConsultationById(mConsultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mPatientInfo = it.patientInfo
                mCaseSummary = it.caseSummary
                mView?.displayPatientInfo(mPatientInfo)
                mView?.displayCaseSummary(mCaseSummary)
            }
        })
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

    override fun onTapSendMessage(message: String) {
        mCareModel.addChatTextMessage(mConsultationId, "doctor", message, onSuccess = {
            mCareModel.getChatMessages(mConsultationId, onFailure = {
                mView?.showErrorMessage(it)
            }).observe(mLifecycleOwner, Observer {
                mView?.displayMessages(it)
            })
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapSendImage(image: Bitmap) {
        mCareModel.addChatImageMessage(mConsultationId, "doctor", image, onSuccess = {
            mCareModel.getChatMessages(mConsultationId, onFailure = {
                mView?.showErrorMessage(it)
            }).observe(mLifecycleOwner, Observer {
                mView?.displayMessages(it)
            })
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }
}