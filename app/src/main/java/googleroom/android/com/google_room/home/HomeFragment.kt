package googleroom.android.com.google_room.home

import android.text.TextUtils
import android.view.View
import googleroom.android.com.google_room.R
import googleroom.android.com.google_room.base.BaseFragment
import googleroom.android.com.google_room.data.bean.Task
import kotlinx.android.synthetic.main.home_fragment_layout.*

/**
 *  Created By handan
 *  CreateDate: 2018/5/23
 *  Desc:
 */
class HomeFragment : BaseFragment(), HomeContract.View, View.OnClickListener {
    override fun onClick(v: View?) {
        if (v == null) return
        when (v) {
            WriteTask -> {
                if (TextUtils.isEmpty(taskId.text.toString())) return
                val task = Task(taskId.text.toString().toLong(),
                        name.text.toString(), System.currentTimeMillis(),
                        isCompleted.isChecked, System.currentTimeMillis())
                mHomePresenter.saveTask(task)
            }
            ReadTasks -> {
                mHomePresenter.getAllTask()
            }
            deleteTaskByName -> {
                if (TextUtils.isEmpty(name.text.toString())) return
                mHomePresenter.deleteTaskByName(name.text.toString())
            }
            deleteTaskById -> {
                if (TextUtils.isEmpty(taskId.text.toString())) return
                mHomePresenter.deleteTaskById(taskId.text.toString().toLong())
            }
            ReadUnDoTasks -> {
                mHomePresenter.getUnDoTasks()
            }
            update_app -> {
//                mHomePresenter.updateApk(activity!!)
//                mHomePresenter.readFile(activity!!)
                mHomePresenter.writeFile(activity!!, "this is a dog 1")
                mHomePresenter.writeFile(activity!!, "this is a pig 2")
                mHomePresenter.writeFile(activity!!, "this is a cat 3")
                mHomePresenter.writeFile(activity!!, "this is a tiger 4")
                mHomePresenter.writeFile(activity!!, "this is a man 5")
                mHomePresenter.writeFile(activity!!, "this is a dog 6")
                mHomePresenter.writeFile(activity!!, "this is a dog 7")
            }
        }
    }

    override var isActive: Boolean = false
        get() = isAdded

    override fun onError(statusCode: Int, errorTip: String) {
    }

    override fun showAllTask(tasks: List<Task>) {
        var str = ""
        for (task in tasks.listIterator()) {
            str += task.id.toString() + task.taskName + task.isCompleted + "\n"
        }
        textView.text = ("任务名称:  \n  $str")
    }

    override fun showText(msg: String) {
        textView.text = msg
    }

    override fun showProgress(p0: Long, p1: Long) {
        tv_progress.text = (((p1 * 100 / p0).toInt()).toString() + "%")
    }

    override fun onCreateView() = Unit

    override fun getLayoutId(): Int = R.layout.home_fragment_layout

    override fun onViewCreated() {
        WriteTask.setOnClickListener(this)
        ReadTasks.setOnClickListener(this)
        isCompleted.setOnClickListener(this)
        deleteTaskById.setOnClickListener(this)
        deleteTaskByName.setOnClickListener(this)
        ReadUnDoTasks.setOnClickListener(this)
        update_app.setOnClickListener(this)
    }

    private lateinit var mHomePresenter: HomeContract.Presenter
    override fun setPresenter(presenter: HomeContract.Presenter) {
        mHomePresenter = presenter
    }

    override fun onResume() {
        super.onResume()
        mHomePresenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        mHomePresenter.unSubscribe()
    }
}