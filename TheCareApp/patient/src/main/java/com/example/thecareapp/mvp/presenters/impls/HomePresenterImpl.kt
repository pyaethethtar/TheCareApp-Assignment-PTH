package com.example.thecareapp.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.models.AuthenticationModel
import com.example.shared.data.models.AuthenticationModelImpl
import com.example.shared.data.models.TheCareModel
import com.example.shared.data.models.TheCareModelImpl
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.data.vos.DoctorVO
import com.example.shared.data.vos.PatientVO
import com.example.shared.mvp.AbstractBasePresenter
import com.example.shared.utils.CONSULTATION_STATUS_START
import com.example.thecareapp.mvp.presenters.HomePresenter
import com.example.thecareapp.mvp.views.HomeView

class HomePresenterImpl: HomePresenter, AbstractBasePresenter<HomeView>() {

    private lateinit var mLifecycleOwner: LifecycleOwner
    private val mCareModel : TheCareModel = TheCareModelImpl
    private var mPatientId : String = ""

    override fun onUiReady(patientId: String, lifecycleOwner: LifecycleOwner) {
        mPatientId = patientId
        mLifecycleOwner = lifecycleOwner

        mCareModel.getPatientById(patientId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it==null){
                mCareModel.getDataFromApiAndSaveToPatientDB(patientId, onSuccess = {}, onFailure = {})
            }
            else{
                requestSpecialities()
            }
        })



        mCareModel.addConsultationListToPatientDB(patientId, onSuccess = {}, onFailure = {})
        requestPatientInfo()
        observeConsultationResponse(lifecycleOwner)
    }

    private fun observeConsultationResponse(lifecycleOwner: LifecycleOwner) {
        mCareModel.getConsultationList(onFailure = {
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val consultationList = arrayListOf<ConsultationVO>()
                for (consultation in it){
                    if (consultation.status== CONSULTATION_STATUS_START){
                        consultationList.add(consultation)
                    }
                }
                if (consultationList.isNotEmpty())  mView?.displayConsultationResponse(consultationList.first())
            }
            else{
                mView?.displayEmptyResponse()
            }
        })
    }

    override fun onTapDoctor(doctorVO: DoctorVO) {
        mView?.displayConfimationDialog(doctorVO.speciality)
    }

    override fun onTapStartConsultation(consultationId: String) {
        mView?.navigateToChatScreen(consultationId)
    }

    override fun onTapSpeciality(speciality: String) {
        mView?.displayConfimationDialog(speciality)
    }


    private fun requestSpecialities(){
        mCareModel.getAllSpecialities(onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                mView?.displaySpecialities(it)
            }
        })
    }

    private fun requestPatientInfo(){
        mCareModel.getPatientById(mPatientId, onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            if (it!=null){
                it.recentDoctorIds?.let {
                    if (it.isNotEmpty())    requestRecentlyConsultedDoctors(it)
                    else mView?.displayEmptyRecentDoctor()
                }
            }
        })
    }

    private fun requestRecentlyConsultedDoctors(doctorIdList: ArrayList<String>){
        mCareModel.addRecentDoctorsToDB(doctorIdList, onSuccess = {}, onFailure = {})

        mCareModel.getDoctorsList(onFailure = {
            mView?.showErrorMessage(it)
        }).observe(mLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                mView?.displayRecentlyConsultedDoctors(it)
            }
            else{
                mView?.displayEmptyRecentDoctor()
            }
        })
    }


}