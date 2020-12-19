package com.example.shared.viewholders

import android.view.View
import com.example.shared.R
import com.example.shared.data.vos.CaseSummaryVO
import kotlinx.android.synthetic.main.item_special_case_summary.view.*

class SpecialCaseSummaryViewHolder(itemView: View) : BaseViewHolder<CaseSummaryVO>(itemView){


    override fun bindData(data: CaseSummaryVO) {
        val mContext = itemView.context
        if (data.question!=mContext.getString(R.string.lbl_dob) && data.question!=mContext.getString(R.string.lbl_height) &&
            data.question!=mContext.getString(R.string.lbl_blood_type) && data.question!=mContext.getString(R.string.lbl_allergic_medicine) &&
            data.question!=mContext.getString(R.string.tv_weight) && data.question!=mContext.getString(R.string.tv_blood_pressure)){

            itemView.tvSpecialQuestion.text = data.question
            itemView.tvSpecialAnswer.text = data.answer
        }
        else{
            itemView.tvSpecialQuestion.visibility = View.GONE
            itemView.tvSpecialAnswer.visibility = View.GONE
        }
    }

}