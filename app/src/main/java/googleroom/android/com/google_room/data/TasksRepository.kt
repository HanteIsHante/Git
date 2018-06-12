package googleroom.android.com.google_room.data

import googleroom.android.com.google_room.data.bean.Task
import io.reactivex.Flowable

/**
 *  Created By handan
 *  CreateDate: 2018/5/11
 *  Desc:
 */
class TasksRepository(var local: TaskDataSource,
                      var remote: TaskDataSource) : TaskDataSource {
    private var mLocal: TaskDataSource = local
    private var mRemote: TaskDataSource = remote

    override fun getTasks(): Flowable<List<Task>> {
        return mLocal.getTasks()
    }


    override fun saveTask(task: Task) {
        mLocal.saveTask(task)
    }

    override fun deleteTaskById(id: Long) {
        mLocal.deleteTaskById(id)
    }

    override fun deleteTaskByName(taskName: String) {
        mLocal.deleteTaskByName(taskName)
    }

    override fun updateTask(task: Task) {
        mLocal.updateTask(task)
    }


    override fun queryUnDoTasks(callBack: TaskDataSource.QueryTasksCallBack) {
        mLocal.queryUnDoTasks(object : TaskDataSource.QueryTasksCallBack {
            override fun onSuccess(taskList: MutableList<Task>) {
                callBack.onSuccess(taskList)
            }

            override fun onFail(statusCode: Int, errorMsg: String) {
                callBack.onFail(statusCode, errorMsg)
            }
        })
    }

    override fun getTask(id: Long): Flowable<Task> {
        return mLocal.getTask(id)
    }

    companion object {
        private var mInstance: TasksRepository? = null

        fun getInstance(local: TaskDataSource,
                        remote: TaskDataSource): TasksRepository {
            if (mInstance == null) {
                synchronized(TasksRepository::class) {
                    if (mInstance == null) {
                        mInstance = TasksRepository(local, remote)
                    }
                }
            }
            return mInstance!!
        }
    }
}


