package com.example.thecarefordoctor.view.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecarefordoctor.adapters.ChatMedicineAdapter
import kotlinx.android.synthetic.main.viewpod_chat_prescription.view.*

class ChatPrescriptionViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var medicationAdapter : ChatMedicineAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()

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
}