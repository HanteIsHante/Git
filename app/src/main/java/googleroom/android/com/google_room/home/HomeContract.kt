package googleroom.android.com.google_room.home

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
    }

    interface Presenter : BasePresenter {

        fun getAllTask()

        fun getUnDoTasks()

        fun saveTask(task: Task)

        fun deleteTaskByName(name: String)

        fun deleteTaskById(taskId: Long)
    }
}