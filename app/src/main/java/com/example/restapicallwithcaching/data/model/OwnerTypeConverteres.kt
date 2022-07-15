package com.example.restapicallwithcaching.data.model
import androidx.room.TypeConverter
import com.example.restapicallwithcaching.data.model.RepoOwner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class OwnerTypeConverter {
    @TypeConverter
    fun fromObj(value: RepoOwner): String {
        val gson = Gson()
        val type = object : TypeToken<RepoOwner>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toObj(value: String): RepoOwner {
        val gson = Gson()
        val type = object : TypeToken<RepoOwner>() {}.type
        return gson.fromJson(value, type)
    }
}