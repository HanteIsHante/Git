package googleroom.android.com.google_room.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import googleroom.android.com.google_room.contants.Contants

/**
 *  CreateDate: 2018/6/7
 *  Desc:
 */
class ShowNotify(context: Context) {


    private lateinit var mNotifyManager: NotificationManager

    init {
        mNotifyManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
    }

    fun createNotifyChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                    NotificationChannel(Contants.NOTIFY_CHANNEL_ID, Contants.NOTIFY_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_LOW)
            mNotifyManager.createNotificationChannel(channel)
        }
    }

    fun showNotify(notifyTitle: String, notifyContent: String) {

    }
}