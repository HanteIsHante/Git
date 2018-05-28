package googleroom.android.com.google_room.data

import googleroom.android.com.google_room.data.bean.Task
import io.reactivex.Flowable


/**
 *  Created By handan
 *  CreateDate: 2018/5/9
 *  Desc:
 */
interface TaskDataSource {

    fun getTasks(): Flowable<List<Task>>

    fun saveTask(task: Task)

    fun deleteTaskById(id: Long)

    fun deleteTaskByName(taskName: String)

    fun updateTask(task: Task)

    interface QueryTasksCallBack {
        fun onSuccess(taskList: MutableList<Task>)
        fun onFail(statusCode: Int, errorMsg: String)
    }

    fun queryUnDoTasks(callBack: QueryTasksCallBack)

    fun getTask(id: Long): Flowable<Task>
}