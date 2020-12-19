package com.example.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.shared.data.vos.ChatVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChatVOTypeConverter {

    @TypeConverter
    fun toJsonString(chats : ArrayList<ChatVO>) : String{
        return Gson().toJson(chats)
    }

    @TypeConverter
    fun toArrayList(chatsString: String) : ArrayList<ChatVO>{
        val chatsType = object : TypeToken<ArrayList<ChatVO>>(){}.type
        return Gson().fromJson(chatsString, chatsType)
    }
}