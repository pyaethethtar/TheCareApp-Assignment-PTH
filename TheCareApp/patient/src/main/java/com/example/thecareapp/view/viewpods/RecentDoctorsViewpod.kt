package com.example.thecareapp.view.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.DoctorVO
import com.example.thecareapp.adapters.RecentDoctorAdapter
import com.example.thecareapp.delegates.RecentDoctorDelegate
import kotlinx.android.synthetic.main.viewpod_recent_doctors.view.*

class RecentDoctorsViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var mDelegate: RecentDoctorDelegate
    private lateinit var mAdapter : RecentDoctorAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setDelegate(delegate: RecentDoctorDelegate){
        mDelegate = delegate

        setUpRecyclerview()
    }

    fun displayRecentDoctors(doctorList: List<DoctorVO>){
        mAdapter.setNewData(doctorList)
    }

    private fun setUpRecyclerview(){
        mAdapter = RecentDoctorAdapter(mDelegate)
        rvRecentDoctors.adapter  = mAdapter

        rvRecentDoctors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}