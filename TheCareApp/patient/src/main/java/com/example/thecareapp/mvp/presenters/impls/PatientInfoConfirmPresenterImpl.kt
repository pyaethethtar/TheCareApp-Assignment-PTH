package com.example.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.utils.REQUEST_STATUS_REQUEST
import com.example.thecareapp.mvp.presenters.PatientInfoConfirmPresenter
import com.example.thecareapp.mvp.views.PatientInfoConfirmView

class PatientInfoConfirmPresenterImpl : PatientInfoConfirmPresenter, AbstractBasePresenter<PatientInfoConfirmView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private var mConsultationRequestVO = ConsultationRequestVO()

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mConsultationRequestVO = mCareModel.consultationRequestVO
        mView?.displayConsultationInfo(mConsultationRequestVO)
    }

    override fun onTapCreateConsultationRequest() {
        mConsultationRequestVO.status = REQUEST_STATUS_REQUEST
        mCareModel.addConsultationRequest(mConsultationRequestVO, onSuccess = {
            mView?.navigateToMainScreen()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }
}