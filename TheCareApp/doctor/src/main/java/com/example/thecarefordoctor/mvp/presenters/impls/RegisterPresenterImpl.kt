package com.example.thecarefordoctor.mvp.presenters.impls

import android.graphics.Bitmap
import com.example.shared.data.models.AuthenticationModel
import com.example.shared.data.models.AuthenticationModelImpl
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.DoctorVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.RegisterPresenter
import com.example.thecarefordoctor.mvp.views.RegisterView

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {


    override fun onUiReady(name: String?, image: String?) {
        if (name!=null && image!=null){
            mView?.displayPatientImageAndName(name, image)
        }
    }

    override fun onTapLogin() {
        mView?.navigateToLoginScreen()
    }
}