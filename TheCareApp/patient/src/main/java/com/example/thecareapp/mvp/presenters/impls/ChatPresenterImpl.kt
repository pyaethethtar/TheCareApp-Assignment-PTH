package com.example.thecareapp.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.ChatPresenter
import com.example.thecareapp.mvp.views.ChatView

class ChatPresenterImpl : ChatPresenter, AbstractBasePresenter<ChatView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mLifecycleOwner: LifecycleOwner
    private var mConsultationId : String = ""
    private var mPatientInfo = PatientVO()
    private var mCaseSummary = arrayListOf<CaseSummaryVO>()

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

    override fun onTapCheckout() {
        mView?.navigateToCheckoutScreen()
    }

    override fun onTapCaseSummary() {
       mView?.navigateToCaseSummaryScreen()
    }

    override fun onTapSendMessage(message: String) {
        mCareModel.addChatTextMessage(mConsultationId, "patient", message, onSuccess = {
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
        mCareModel.addChatImageMessage(mConsultationId, "patient", image, onSuccess = {
            mCareModel.getChatMessages(mConsultationId, onFailure = {
                mView?.showErrorMessage(it)
            }).observe(mLifecycleOwner, Observer {
                mView?.displayMessages(it)
            })
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapPrescription() {
        mView?.navigateToPrescriptionScreen()
    }

}