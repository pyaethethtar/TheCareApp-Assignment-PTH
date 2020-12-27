package com.example.thecareapp.view.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.viewpod_new_address.view.*

class NewAddressViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun getAddress() : String{
        val address = etFullAddress.text.toString()+", "+spinnerTownship.selectedItem.toString()+", "+
                spinnerDivision.selectedItem.toString()
        return address
    }
}