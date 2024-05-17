package almerti.egline.data.source.database.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value : List<String>) : String {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value : String) : List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}
