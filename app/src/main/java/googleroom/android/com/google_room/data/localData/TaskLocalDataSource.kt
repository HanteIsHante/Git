package googleroom.android.com.google_room.data.localData

import android.content.Context
import googleroom.android.com.google_room.data.TaskDataSource
import googleroom.android.com.google_room.data.bean.Task
import io.reactivex.Flowable

/**
 *  Created By handan
 *  CreateDate: 2018/5/9
 *  Desc:
 */
class TaskLocalDataSource(context: Context)
    : TaskDataSource {

    private val taskDao = ToDoDataSource.getInstance(context).taskDao()

    override fun saveTask(task: Task) {
        taskDao.insertTask(task)
    }

    override fun deleteTaskById(id: Long) {
        taskDao.deleteTaskById(id)
    }

    override fun deleteTaskByName(taskName: String) {
        taskDao.deleteTaskByName(taskName)
    }

    override fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override fun getTasks(): Flowable<List<Task>> {
        return taskDao.getTasks()
    }

    override fun queryUnDoTasks(callBack: TaskDataSource.QueryTasksCallBack) {
        val tasks = taskDao.getUnDoTasks()
        if (!tasks.isEmpty()) {
            callBack.onSuccess(tasks)
        } else {
            callBack.onFail(404, "无查询数据")
        }
    }

    override fun getTask(id: Long): Flowable<Task> {
        return taskDao.getTaskById(id)
    }

    companion object {
        private var mInstance: TaskLocalDataSource? = null
        fun getInstance(context: Context): TaskLocalDataSource {
            if (mInstance == null) {
                synchronized(this) {
                    if (mInstance == null) {
                        mInstance = TaskLocalDataSource(context)
                    }
                }
            }
            return mInstance!!
        }
    }
}