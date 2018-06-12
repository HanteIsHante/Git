package googleroom.android.com.google_room.data.dao

import android.arch.persistence.room.*
import googleroom.android.com.google_room.data.bean.Task
import io.reactivex.Flowable

/**
 *  Created By handan
 *  CreateDate: 2018/5/9
 *  Desc: 用于执行 具体的增删改查 等 相关业务
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flowable<List<Task>>

    /*true = 1 false = 0*/
    @Query("SELECT * FROM tasks WHERE isCompleted = 0")
    fun getUnDoTasks(): MutableList<Task>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Long): Flowable<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("UPDATE tasks SET isCompleted =:isCompleted WHERE id =:taskId")
    fun updateTask(taskId: Long, isCompleted: Boolean)

    @Query("DELETE FROM tasks WHERE id =:taskId")
    fun deleteTaskById(taskId: Long)

    @Query("DELETE FROM tasks WHERE taskName =:taskName")
    fun deleteTaskByName(taskName: String)

    @Query("DELETE FROM tasks")
    fun deleteTasks()

    @Query("DELETE FROM tasks WHERE isCompleted = 1")
    fun deleteCompletedTask(): Int
}