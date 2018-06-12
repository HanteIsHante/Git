package googleroom.android.com.google_room.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import java.util.*

object PermissionUtil {
    /**
     * 检查和申请权限
     *
     * @param permisstion
     */
    fun checkPermisstion(context: Activity, permisstion: Array<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permisstions = LinkedList<String>()
            for (per in permisstion) {
                if (context.checkSelfPermission(per) == PackageManager.PERMISSION_DENIED) {
                    permisstions.add(per)
                }
            }
            if (permisstions.size > 0) {
                context.requestPermissions(permisstions.toTypedArray(), 1)
                permisstions.clear()
            }
        }
    }
}