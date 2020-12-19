package com.example.thecarefordoctor.view.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.ConsultationRequestVO
import com.example.thecarefordoctor.adapters.RequestAdapter
import com.example.thecarefordoctor.delegates.RequestItemDelegate
import kotlinx.android.synthetic.main.viewpod_consultation_request.view.*

class ConsultationRequestViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var mDelegate: RequestItemDelegate
    private lateinit var mAdapter : RequestAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setDelegate(delegate: RequestItemDelegate){
        mDelegate = delegate

        setUpRecyclerview()
    }

    private fun setUpRecyclerview(){
        mAdapter = RequestAdapter(mDelegate)
        rvRequests.adapter = mAdapter

        rvRequests.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun displayRequests(requests : List<ConsultationRequestVO>){
        mAdapter.setNewData(requests)
    }
}