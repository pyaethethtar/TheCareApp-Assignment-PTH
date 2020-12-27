package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.utils.CONSULTATION_STATUS_END
import com.example.shared.utils.CONSULTATION_STATUS_START
import com.example.thecareapp.mvp.presenters.ConsultationsPresenter
import com.example.thecareapp.mvp.views.ConsultationsView

class ConsultationsPresenterImpl : ConsultationsPresenter, AbstractBasePresenter<ConsultationsView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl


    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

        val mPatientId = mCareModel.mPatientId
        mCareModel.addConsultationListToPatientDB(mPatientId, onSuccess = {}, onFailure = {})
        requestConsultationList(lifecycleOwner)
    }

    override fun onTapPrescription(consultationId: String) {
        mView?.displayPrescriptionDialog(consultationId)
    }

    override fun onTapSendMessage(consultationId: String) {
        mView?.navigateToChatScreen(consultationId, mCareModel.mPatientId)
    }

    private fun requestConsultationList(lifecycleOwner: LifecycleOwner){
        mCareModel.getConsultationList(onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val consultationList = arrayListOf<ConsultationVO>()
                for (consultation in it){
                    if (consultation.status== CONSULTATION_STATUS_END){
                        consultationList.add(consultation)
                    }
                }
                mView?.displayConsultations(consultationList)
            }
        })
    }
}