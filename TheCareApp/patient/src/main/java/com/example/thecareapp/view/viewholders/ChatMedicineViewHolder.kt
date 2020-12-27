package com.example.thecareapp.view.viewholders

import android.view.View
import com.example.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_chat_medicine.view.*

class ChatMedicineViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {


    override fun bindData(data: String) {
        itemView.tvMedicine.text = data
    }
}