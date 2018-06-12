package googleroom.android.com.google_room.net.ok

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

/**
 *  Created By handan
 *  CreateDate: 2018/5/29
 *  Desc: OkHttp model
 */
class OkHttpModel {
    private var mOkHttpClient = OkHttpClient()
    private val mMediaType = MediaType.parse("application/json; charset=utf-8")

    /**
     * Post
     */
    fun post(requestJson: String, callBack: HttpCallBack.JsonCallback) {
        val requestBody = RequestBody.create(mMediaType, requestJson)
        val request = Request.Builder()
                .url("")
                .post(requestBody)
                .build()
        val postResponse = mOkHttpClient.newCall(request).execute()
        if (postResponse.isSuccessful) {
            val string = postResponse.body()?.string()
        } else {
        }
    }

    /**
     * get
     */
    fun get(callBack: HttpCallBack.JsonCallback) {
        val request = Request.Builder()
                .url("http://api.douban.com/v2/movie/nowplaying?apikey=0df993c66c0c636e29ecbb5344252a4a")
                .build()
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                if (e != null) {
                    callBack.onFailure(e.hashCode(), e)
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null && response.isSuccessful) {
                    val result = response.body()?.string()
                    val jsonObject: JSONObject
                    try {
                        jsonObject = JSONObject(result)
                        callBack.onResponse(jsonObject)
                    } catch (e: Exception) {
                    } finally {
                        response.close()
                    }
                }
            }
        })
    }
}