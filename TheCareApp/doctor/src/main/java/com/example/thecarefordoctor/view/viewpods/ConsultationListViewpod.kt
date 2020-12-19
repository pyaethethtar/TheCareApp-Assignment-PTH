package com.example.thecarefordoctor.view.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.ConsultationVO
import com.example.thecarefordoctor.adapters.ConsultationAdapter
import com.example.thecarefordoctor.delegates.ConsultationItemDelegate
import kotlinx.android.synthetic.main.viewpod_consultation_list.view.*

class ConsultationListViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var mDelegate : ConsultationItemDelegate
    private lateinit var mAdapter : ConsultationAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setDelegate(delegate: ConsultationItemDelegate){
        mDelegate = delegate

        setUpRecyclerview()
    }

    private fun setUpRecyclerview(){
        mAdapter = ConsultationAdapter(mDelegate)
        rvConsultationHistory.adapter = mAdapter

        rvConsultationHistory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun displayConsultationList(consultations : List<ConsultationVO>){
        mAdapter.setNewData(consultations)
    }
}