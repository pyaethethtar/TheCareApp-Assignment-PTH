package com.example.thecarefordoctor.mvp.presenters.impls

import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.DoctorInfoPresenter
import com.example.thecarefordoctor.mvp.views.DoctorInfoView

class DoctorInfoPresenterImpl : DoctorInfoPresenter, AbstractBasePresenter<DoctorInfoView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl

    override fun onTapCreateAccount(doctorVO: DoctorVO) {
        mCareModel.addNewDoctor(doctorVO, onSuccess = {
            mView?.navigateToMainScreen(doctorVO.doctorId)
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapBack() {
        mView?.navigateToRegisterScreen()
    }
}