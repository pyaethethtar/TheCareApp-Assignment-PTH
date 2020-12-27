package com.example.thecareapp.view.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecareapp.mvp.presenters.ConsultationsPresenter
import kotlinx.android.synthetic.main.item_consultation.view.*

class ConsultationViewHolder(itemView: View, private val mPresenter: ConsultationsPresenter) : BaseViewHolder<ConsultationVO>(itemView) {

    override fun bindData(data: ConsultationVO) {
        Glide.with(itemView.context).load(data.doctorInfo.doctorProfileImage).into(itemView.ivDoctor)
        itemView.tvDoctorName.text = data.doctorInfo.doctorName
        itemView.tvDoctorSpeciality.text = data.doctorInfo.speciality
        itemView.tvConsultationDate.text = data.consultationDate

        itemView.btnPrescription.setOnClickListener {
            mPresenter.onTapPrescription(data.consultationId)
        }

        itemView.btnSendMessage.setOnClickListener {
            mPresenter.onTapSendMessage(data.consultationId)
        }
    }
}