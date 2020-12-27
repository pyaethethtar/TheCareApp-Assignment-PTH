package com.example.thecarefordoctor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shared.adapters.BaseAdapter
import com.example.shared.data.vos.ChatVO
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.R
import com.example.thecarefordoctor.view.viewholders.ChatMessageViewHolder
import kotlinx.android.synthetic.main.item_chat_message.view.*

class ChatMessageAdapter : BaseAdapter<BaseViewHolder<ChatVO>, ChatVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChatVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false)
        return ChatMessageViewHolder(view)
    }

}