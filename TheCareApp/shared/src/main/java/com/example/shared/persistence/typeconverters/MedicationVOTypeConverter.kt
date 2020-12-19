package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.MedicationVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MedicationVOTypeConverter {

    @TypeConverter
    fun toJsonString(medications : ArrayList<MedicationVO>) : String{
        return Gson().toJson(medications)
    }

    @TypeConverter
    fun toArrayList(medicationsString: String) : ArrayList<MedicationVO>{
        val medicationsType = object : TypeToken<ArrayList<MedicationVO>>(){}.type
        return Gson().fromJson(medicationsString, medicationsType)
    }
}