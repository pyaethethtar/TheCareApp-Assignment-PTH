package com.example.thecarefordoctor.view.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.delegates.RequestItemDelegate
import kotlinx.android.synthetic.main.item_consultation_request.view.*

class RequestViewHolder(itemView: View, private val mDelegate: RequestItemDelegate) :  BaseViewHolder<ConsultationRequestVO>(itemView){

    override fun bindData(data: ConsultationRequestVO) {
        Glide.with(itemView.context).load(data.patientInfo.patientProfileImage).circleCrop().into(itemView.ivPatient)
        itemView.tvPatientName.text = data.patientInfo.patientName
        val caseSummary = data.patientInfo.patientCaseSummary ?: arrayListOf()
        for (cs in caseSummary){
            if (cs.question == "မွေးနေ့") {
                itemView.tvPatientDob.text = "${cs.question} : ${cs.answer}"
            }
        }

        itemView.setOnClickListener {
            mDelegate.onTapConsultationRequest(data.requestId)
        }
        itemView.btnCallLater.setOnClickListener {
            mDelegate.onTapLater()
        }
        itemView.btnAccept.setOnClickListener {
            mDelegate.onTapAccept(data)
        }
        itemView.btnPostpone.setOnClickListener {
            mDelegate.onTapPostpone()
        }
    }
}