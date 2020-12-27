package com.example.thecarefordoctor.view.viewholders

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.data.vos.ConsultationVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.delegates.ConsultationItemDelegate
import kotlinx.android.synthetic.main.item_consultation_history.view.*

class ConsultationViewHolder(itemView: View, private val mDelegate: ConsultationItemDelegate) : BaseViewHolder<ConsultationVO>(itemView) {

    override fun bindData(data: ConsultationVO) {
        Glide.with(itemView.context).load(data.patientInfo.patientProfileImage).circleCrop().into(itemView.ivPatient)
        itemView.tvPatientName.text = data.patientInfo.patientName
        for (cs in data.caseSummary){
            if (cs.question == "မွေးနေ့") {
                itemView.tvPatientDob.text = cs.answer
            }
        }

        itemView.btnCaseSummary.setOnClickListener {
            mDelegate.onTapConsultationHistory(data.consultationId)
        }
        itemView.btnPrescription.setOnClickListener {
            mDelegate.onTapPrescription(data.consultationId)
        }
        itemView.btnNote.setOnClickListener {
            mDelegate.onTapNotes(data.consultationId)
        }
        itemView.btnSendMessage.setOnClickListener {
            mDelegate.onTapSendMessage(data.consultationId)
        }
    }
}