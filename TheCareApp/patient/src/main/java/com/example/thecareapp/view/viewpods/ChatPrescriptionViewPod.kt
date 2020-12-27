package com.example.thecareapp.view.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecareapp.adapters.ChatMedicineAdapter
import com.example.thecareapp.mvp.presenters.ChatPresenter
import kotlinx.android.synthetic.main.viewpod_chat_prescription.view.*

class ChatPrescriptionViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var medicationAdapter : ChatMedicineAdapter
    private lateinit var mPresenter: ChatPresenter

    override fun onFinishInflate() {
        super.onFinishInflate()

        setUpListener()
    }

    fun setPresenter(presenter : ChatPresenter){
        mPresenter = presenter

        setUpRecyclerview()
    }


    private fun setUpRecyclerview(){
        medicationAdapter = ChatMedicineAdapter()
        rvMedication.adapter = medicationAdapter
        rvMedication.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun displayMedicines(medicines : ArrayList<String>){
        medicationAdapter.setNewData(medicines)
    }

    private fun setUpListener(){
        btnCheckout.setOnClickListener {
            mPresenter.onTapCheckout()
        }
    }


}