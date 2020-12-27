package com.example.thecarefordoctor.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.MedicationVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.thecarefordoctor.mvp.presenters.PrescriptionPresenter
import com.example.thecarefordoctor.mvp.views.PrescriptionView

class PrescriptionPresenterImpl : PrescriptionPresenter, AbstractBasePresenter<PrescriptionView>() {

    private val mCareModel : TheCareModel = TheCareModelImpl
    private lateinit var mConsultationId: String

    override fun onUiReady(consultationId: String, lifecycleOwner: LifecycleOwner) {
        mConsultationId = consultationId
        mCareModel.mConsultationId = consultationId

        mCareModel.getSpeciality(mCareModel.mSpeciality, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayMedicines(it.medication)
            }
        })
    }

    override fun onTapAddMedicine(medicationVO: MedicationVO) {
        mView?.displayMedicationDialog(medicationVO.medicine, medicationVO.price)
    }

    override fun onTapRemoveMedicine(medicationVO: MedicationVO) {
        mCareModel.medicationList.remove(medicationVO)
    }

    override fun onTapSearch() {
        TODO("Not yet implemented")
    }

    override fun onTapAdd(medication: MedicationVO) {
        mCareModel.medicationList.add(medication)
    }

    override fun onTapFinish() {
        mCareModel.addMedicationListToConsultation(mConsultationId, mCareModel.medicationList)

        mCareModel.finishConsultation(mConsultationId, onSuccess = {
            mView?.navigateToChatScreen()
        }, onFailure = {
            mView?.showErrorMessage(it)
        })
    }


}