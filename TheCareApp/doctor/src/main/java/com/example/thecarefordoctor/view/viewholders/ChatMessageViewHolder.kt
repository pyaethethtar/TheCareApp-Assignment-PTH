package com.example.thecarefordoctor.view.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.shared.data.vos.ChatVO
import com.example.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_chat_message.view.*

class ChatMessageViewHolder(itemView: View) : BaseViewHolder<ChatVO>(itemView) {


    override fun bindData(data: ChatVO) {
        data.doctor?.let {
            if (it!=""){
                itemView.tvDoctorMessage.visibility = View.VISIBLE
                itemView.tvDoctorMessage.text = it
                itemView.tvDoctorSendingTime.text = data.sendingTime
            }
        }
        data.patient?.let {
            if (it!=""){
                itemView.tvPatientMessage.visibility = View.VISIBLE
                itemView.tvPatientMessage.text = it
                itemView.tvPatientSendingTime.text = data.sendingTime
            }
        }
        data.doctorImage?.let {
            if (it!=""){
                itemView.ivDoctorMessage.visibility = View.VISIBLE
                Glide.with(itemView.context).load(it).circleCrop().into(itemView.ivDoctorMessage)
                itemView.tvDoctorSendingTime.text = data.sendingTime
            }
        }
        data.patientImage?.let {
            if (it!=""){
                itemView.ivPatientMessage.visibility = View.VISIBLE
                Glide.with(itemView.context).load(it).circleCrop().into(itemView.ivPatientMessage)
                itemView.tvPatientSendingTime.text = data.sendingTime
            }
        }
    }
}