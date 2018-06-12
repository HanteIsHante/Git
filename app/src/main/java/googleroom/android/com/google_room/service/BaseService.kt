package googleroom.android.com.google_room.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat

/**
 *  CreateDate: 2018/6/12
 *  Desc:
 */
class BaseService : Service() {


    override fun onCreate() {
        super.onCreate()
        // 启动一个前台通知服务
        startForeground(109, NotificationCompat.Builder(applicationContext, "ServiceForeground").build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}