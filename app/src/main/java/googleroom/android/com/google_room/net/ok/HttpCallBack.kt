package googleroom.android.com.google_room.net.ok

import org.json.JSONObject

/**
 *  Created By handan
 *  CreateDate: 2018/5/29
 *  Desc:
 */
interface HttpCallBack {
    interface JsonCallback {
        fun onResponse(responseJson: JSONObject)

        fun onFailure(code: Int, e: Exception)
    }
}