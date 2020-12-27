package com.example.thecareapp.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.CheckoutVO
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DeliveryVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.utils.CONSULTATION_STATUS_END
import com.example.thecareapp.mvp.presenters.ChatPresenter
import com.example.thecareapp.mvp.views.ChatView

class ChatPresenterImpl : ChatPresenter, AbstractBasePresenter<ChatView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mLifecycleOwner: LifecycleOwner
    private var mConsultationId : String = ""
    private var mConsultationVO = ConsultationVO()
    private var mPatientId : String = ""

    override fun onUiReady(consultationId: String, patientId: String,  lifecycleOwner: LifecycleOwner) {
        mConsultationId = consultationId
        mLifecycleOwner = lifecycleOwner
        mPatientId = patientId

        requestConsultationAndObserveMessages(lifecycleOwner)
        requestChatMessages(consultationId, lifecycleOwner)
        requestMedicationList(consultationId, lifecycleOwner)
    }


    override fun onTapCaseSummary() {
       mView?.navigateToPatientInfoDetailsScreen(mConsultationId)
    }

    override fun onTapSendMessage(message: String) {
        mCareModel.addChatTextMessage(mConsultationId, "patient", message, onSuccess = {
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapSendImage(image: Bitmap) {
        mCareModel.addChatImageMessage(mConsultationId, "patient", image, onSuccess = {
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapCheckout() {
        val checkoutVO = CheckoutVO()
        checkoutVO.patientInfo = mConsultationVO.patientInfo
        checkoutVO.prescriptions = mConsultationVO.prescriptions
        var total = 0
        mConsultationVO.prescriptions?.let {
            for (prescription in it){
                val price = prescription.price?:0
                val count = prescription.count?:0
                val subtotal = price*count
                total= total+subtotal
            }
        }

        checkoutVO.totalAmount = total

        mCareModel.addCheckout(checkoutVO, onSuccess = {
            mCareModel.addCheckoutInfoToDB(it, onSuccess = {}, onFailure = {})
            mView?.navigateToCheckoutScreen(it)
        }, onFailure = {
            mView?.showErrorMessage(it)
        })

    }


    private fun requestConsultationAndObserveMessages(lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationById(mConsultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mConsultationVO = it
                if (it.status== CONSULTATION_STATUS_END){
                    mView?.hideKeyboard()
                    mCareModel.addRecentlyConsultedDoctor(it.doctorInfo.doctorId, mPatientId, onSuccess = {}, onFailure = {})
                }
                else{
                    mView?.displayKeyboard()
                }
                mCareModel.getChatMessagesAndSaveToDB(mConsultationVO, onSuccess = {}, onFailure = {})
                mView?.displayDoctorInfo(it.doctorInfo)
                mView?.displayPatientInfo(it)
            }
        })
    }

    private fun requestChatMessages(consultationId: String, lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            it.chats?.let {
                if (it.isNotEmpty())    mView?.displayMessages(it)
            }
        })
    }

    private fun requestMedicationList(consultationId: String, lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            it.prescriptions?.let {
                if (it.isNotEmpty()) mView?.displayMedicineRecommendation(it)
            }
        })
    }


}