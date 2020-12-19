package com.example.shared.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.shared.viewholders.BaseViewHolder

abstract class BaseAdapter<T : BaseViewHolder<W>, W> : RecyclerView.Adapter<T>() {

    protected var mData : ArrayList<W> = arrayListOf()

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setNewData(data: List<W>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun appendData(data: W){
        mData.add(data)
        notifyDataSetChanged()
    }
}