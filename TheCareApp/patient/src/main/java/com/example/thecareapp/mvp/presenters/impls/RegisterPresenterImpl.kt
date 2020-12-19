package com.example.thecareapp.mvp.presenters.impls

import android.graphics.Bitmap
import android.util.Log
import com.example.shared.data.models.AuthenticationModel
import com.example.shared.data.models.AuthenticationModelImpl
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecareapp.mvp.presenters.RegisterPresenter
import com.example.thecareapp.mvp.views.RegisterView

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