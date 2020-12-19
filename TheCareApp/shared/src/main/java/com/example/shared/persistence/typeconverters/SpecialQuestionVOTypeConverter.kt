package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.SpecialQuestionVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpecialQuestionVOTypeConverter {

    @TypeConverter
    fun toJsonString(specialQuestions : ArrayList<SpecialQuestionVO>) : String{
        return Gson().toJson(specialQuestions)
    }

    @TypeConverter
    fun toArrayList(specialQuestionsString: String) : ArrayList<SpecialQuestionVO>{
        val specialQuestionsType = object : TypeToken<ArrayList<SpecialQuestionVO>>(){}.type
        return Gson().fromJson(specialQuestionsString, specialQuestionsType)
    }

}