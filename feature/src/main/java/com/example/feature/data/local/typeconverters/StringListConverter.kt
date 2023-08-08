package com.example.feature.data.local.typeconverters

import androidx.room.TypeConverter

class StringListConverter {

    private companion object {
        const val separator = "__,__"
    }


    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(separator)
    }

    @TypeConverter
    fun stringToListString(string: String): List<String> {
        return string.split(separator)
    }
}