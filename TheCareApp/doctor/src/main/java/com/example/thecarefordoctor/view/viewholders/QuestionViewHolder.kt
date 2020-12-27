package com.example.thecarefordoctor.view.viewholders

import android.view.View
import com.example.shared.viewholders.BaseViewHolder
import com.example.thecarefordoctor.delegates.QuestionItemDelegate
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionViewHolder(itemView: View, private val mDelegate: QuestionItemDelegate) : BaseViewHolder<String>(itemView) {


    override fun bindData(data: String) {
        itemView.tvQuestion.text = data

        itemView.setOnClickListener {
            mDelegate.onTapQuestion(data)
        }
    }
}