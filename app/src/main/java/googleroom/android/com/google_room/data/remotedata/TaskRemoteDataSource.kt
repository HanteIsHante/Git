package googleroom.android.com.google_room.data.remotedata

import googleroom.android.com.google_room.data.TaskDataSource
import googleroom.android.com.google_room.data.bean.Task
import io.reactivex.Flowable

/**
 *  Created By handan
 *  CreateDate: 2018/5/15
 *  Desc:
 */
class TaskRemoteDataSource : TaskDataSource {
    override fun deleteTaskByName(taskName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTasks(): Flowable<List<Task>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTaskById(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryUnDoTasks(callBack: TaskDataSource.QueryTasksCallBack) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTask(id: Long): Flowable<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        var mInstance: TaskRemoteDataSource? = null
        fun getInstance(): TaskRemoteDataSource {
            if (mInstance == null) {
                synchronized(this) {
                    if (mInstance == null) {
                        mInstance = TaskRemoteDataSource()
                    }
                }
            }
            return mInstance!!
        }
    }
}