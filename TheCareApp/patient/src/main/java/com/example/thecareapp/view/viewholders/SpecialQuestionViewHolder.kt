package com.example.thecareapp.view.viewholders

import android.view.View
import com.example.shared.data.vos.SpecialQuestionVO
import com.example.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_special_question.view.*

class SpecialQuestionViewHolder(itemView: View) : BaseViewHolder<SpecialQuestionVO>(itemView) {

    override fun bindData(data: SpecialQuestionVO) {
        itemView.tvSpecialQuestion.text = data.specialQuestion
    }
}