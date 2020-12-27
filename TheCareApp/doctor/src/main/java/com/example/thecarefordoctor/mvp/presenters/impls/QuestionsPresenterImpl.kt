package com.example.thecarefordoctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.QuestionsPresenter
import com.example.thecarefordoctor.mvp.views.QuestionsView

class QuestionsPresenterImpl : QuestionsPresenter, AbstractBasePresenter<QuestionsView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mSpeciality : String
    private lateinit var mConsultationId: String

    override fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner) {
        mSpeciality = mCareModel.mSpeciality
        mConsultationId = consultationId

        mCareModel.getSpeciality(mSpeciality, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayQuestions(it.doctorQuestions)
            }
        })
    }

    override fun onTapQuestion(question: String) {
        mCareModel.addChatTextMessage(mConsultationId, "doctor", question, onSuccess = {
            mView?.navigateToChatScreen(question)
        },onFailure = {
            mView?.showErrorMessage(it)
        })

    }
}