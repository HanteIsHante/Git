package googleroom.android.com.google_room.home

import android.content.Context
import googleroom.android.com.google_room.BasePresenter
import googleroom.android.com.google_room.BaseView
import googleroom.android.com.google_room.data.bean.Task

/**
 *  Created By handan
 *  CreateDate: 2018/5/10
 *  Desc:
 */
interface HomeContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean

        fun showAllTask(tasks: List<Task>)

        fun onError(statusCode: Int, errorTip: String)

        fun showText(msg: String)

        fun showProgress(p0: Long, p1: Long)
    }

    interface Presenter : BasePresenter {

        fun getAllTask()

        fun getUnDoTasks()

        fun saveTask(task: Task)

        fun deleteTaskByName(name: String)

        fun deleteTaskById(taskId: Long)

        fun writeFile(context: Context, msg: String)

        fun readFile(context: Context)

        fun updateApk(context: Context)
    }
}