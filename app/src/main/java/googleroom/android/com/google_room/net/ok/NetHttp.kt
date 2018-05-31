package googleroom.android.com.google_room.net.ok

import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 *  Created By handan
 *  CreateDate: 2018/5/29
 *  Desc: 封装网络请求
 *  Interceptor： 两种 ：APP层面的拦截器（Application Interception）、
 *                      网络请求层面的拦截器(Network Interception)；
 */
class NetHttp : Interceptor, HostnameVerifier {

    private var mOkHttpClient: OkHttpClient
    private val mMediaType = MediaType.parse("application/json; charset=utf-8")

    init {
        mOkHttpClient = OkHttpClient.Builder()
                .addInterceptor(this) //Application拦截器
                .hostnameVerifier(this)
                .connectTimeout(3000L, TimeUnit.MILLISECONDS)
                .readTimeout(5000L, TimeUnit.MILLISECONDS)
                .build()
    }

    /**
     * get 请求
     * 如果带有参数, 直接拼接在url 后面
     */
    fun get(url: String, requestParams: List<Pair<String, String>>?,
            requestCallBack: HttpCallBack.JsonCallback) {

        val newUrlBuilder = HttpUrl.parse(url)!!.newBuilder()
        if (requestParams != null) {
            for (pair in requestParams.iterator()) {
                newUrlBuilder.addQueryParameter(pair.first, pair.second)
            }
        }
        val getRequest = Request.Builder()
                .url(newUrlBuilder.build())
                .get()
                .build()
        mOkHttpClient.newCall(getRequest).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    try {
                        val jsonObject = JSONObject(responseBody!!.string())
                        requestCallBack.onResponse(jsonObject)
                    } catch (e: Exception) {
                    } finally {
                        responseBody?.close()
                    }
                }
            }
        })
    }

    /**
     * Post  请求
     */
    fun post(url: String, requestJson: JSONObject, requestCallBack: HttpCallBack.JsonCallback) {

        val requestBody = RequestBody.create(mMediaType, requestJson.toString())
        val build = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
        mOkHttpClient.newCall(build).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            val jsonObject: JSONObject
                            try {
                                jsonObject = JSONObject(responseBody.string())
                                requestCallBack.onResponse(jsonObject)
                            } catch (e: JSONException) {
                            } catch (e: IOException) {
                            } finally {
                                response.close()
                                responseBody.close()
                            }
                        }
                    } else {
                        val code = response.code()
                    }
                } else {

                }
            }
        })
    }

    /**
     * 日志拦截器
     */
    override fun intercept(chain: Interceptor.Chain?): Response? {
        if (chain == null) return null
        val request = chain.request()
        val url = request.url()
        val urlHost = url.host()
        val requestBody = request.body()

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * hostnameVerifier
     */
    override fun verify(hostname: String?, session: SSLSession?): Boolean {
        return true
    }


    companion object {
        private var mInstance: NetHttp? = null

        fun getInstance(): NetHttp {
            if (mInstance == null) {
                synchronized(NetHttp::class.java) {
                    if (mInstance == null) {
                        mInstance = NetHttp()
                    }
                }
            }
            return mInstance!!
        }
    }
}