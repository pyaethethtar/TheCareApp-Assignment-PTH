package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.NotePresenter
import com.example.thecarefordoctor.mvp.views.NoteView

class NotePresenterImpl : NotePresenter, AbstractBasePresenter<NoteView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mConsultationId: String

    override fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner) {
        mConsultationId = consultationId
        mCareModel.getConsultationById(consultationId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayConsultationInfo(it)
            }
        })
    }

    override fun onTapSave(note: String) {
        mCareModel.addNoteToConsultation(mConsultationId, note, onSuccess = {
            mView?.navigateToChatScreen()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }
}