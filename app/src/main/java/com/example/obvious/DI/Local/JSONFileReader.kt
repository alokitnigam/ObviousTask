package com.example.obvious.DI.Local

import android.content.Context
import com.example.obvious.DI.Models.PodModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream


class JSONFileReader(private val context: Context) {
    var list: ArrayList<PodModel> = ArrayList()

    init {
        loadJSONFromAsset()
    }

    public fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val inputStream: InputStream = context.assets.open("data.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun parseJSON():ArrayList<PodModel>{
        try {
            val jsonArray = JSONArray(loadJSONFromAsset())

            for (i in 0 until jsonArray.length() ){
                val jsonObject = jsonArray.getJSONObject(i)
                val podModel = PodModel(
                    copyright = jsonObject.optString("copyright"),
                    date = jsonObject.optString("date"),
                    explanation = jsonObject.optString("explanation"),
                    hdurl = jsonObject.optString("hdurl"),
                    media_type = jsonObject.optString("media_type"),
                    service_version = jsonObject.optString("service_version"),
                    title = jsonObject.optString("title"),
                    url = jsonObject.optString("url")
                )

                list.add(podModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    public fun getPodList(): ArrayList<PodModel> {
        if (list.isNotEmpty())
            return list
        else
            return parseJSON()
    }
}