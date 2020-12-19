package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.CaseSummaryVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CaseSummaryVOTypeConverter {

    @TypeConverter
    fun toJsonString(casesummary: ArrayList<CaseSummaryVO>) : String{
        return Gson().toJson(casesummary)
    }

    @TypeConverter
    fun toArrayList(casesummaryString : String) : ArrayList<CaseSummaryVO>{
        val casesummaryType = object : TypeToken<ArrayList<CaseSummaryVO>>(){}.type
        return Gson().fromJson(casesummaryString, casesummaryType)
    }
}