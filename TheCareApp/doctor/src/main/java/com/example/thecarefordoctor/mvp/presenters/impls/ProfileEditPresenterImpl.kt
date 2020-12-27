package com.example.thecarefordoctor.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.ProfileEditPresenter
import com.example.thecarefordoctor.mvp.views.ProfileEditView

class ProfileEditPresenterImpl : ProfileEditPresenter, AbstractBasePresenter<ProfileEditView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mDoctorId: String
    private var mDoctorVO = DoctorVO()

    override fun onUiReady(doctorId: String, lifecycleOwner: LifecycleOwner) {
        mDoctorId = doctorId

        mCareModel.getDoctorById(doctorId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mDoctorVO = it
                mView?.displayDoctorInfo(it)
            }
        })
    }

    override fun onTapSave(doctorVO: DoctorVO, image: Bitmap) {
        doctorVO.doctorEmail = mDoctorVO.doctorEmail
        doctorVO.doctorPassword = mDoctorVO.doctorPassword

        mCareModel.updateDoctorInfo(doctorVO, image, onSuccess = {
            mCareModel.addDoctorInfoToDB(doctorVO.doctorId, onSuccess = {}, onFailure = {})
            mView?.navigateToProfileScreen()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapBack() {
        mView?.navigateToProfileScreen()
    }
}