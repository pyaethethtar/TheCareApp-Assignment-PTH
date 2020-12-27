package com.example.thecarefordoctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shared.adapters.BaseAdapter
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.view.viewholders.ChatMedicineViewHolder

class ChatMedicineAdapter : BaseAdapter<BaseViewHolder<String>, String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_medicine, parent, false)
        return ChatMedicineViewHolder(view)
    }
}