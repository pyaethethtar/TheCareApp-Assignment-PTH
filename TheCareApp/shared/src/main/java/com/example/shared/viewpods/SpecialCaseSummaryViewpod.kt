package com.example.shared.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.adapters.SpecialCaseSummaryAdapter
import com.example.shared.data.vos.CaseSummaryVO
import kotlinx.android.synthetic.main.viewpod_special_case_summary.view.*

class SpecialCaseSummaryViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var mAdapter : SpecialCaseSummaryAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        mAdapter = SpecialCaseSummaryAdapter()
        rvSpecialCaseSummary.adapter = mAdapter
        rvSpecialCaseSummary.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun displaySpecialCaseSummary(casesummary : ArrayList<CaseSummaryVO>){
        mAdapter.setNewData(casesummary)
    }


}