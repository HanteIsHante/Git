package googleroom.android.com.google_room.home

import googleroom.android.com.google_room.data.TasksRepository
import googleroom.android.com.google_room.data.bean.Task
import googleroom.android.com.google_room.rxutil.BaseSchedulerProvider
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *  Created By handan
 *  CreateDate: 2018/5/10
 *  Desc:
 */
class HomePresenter(tasksRepository: TasksRepository,
                    baseSchedulerProvider: BaseSchedulerProvider,
                    view: HomeContract.View)
    : HomeContract.Presenter {

    private var mBaseSchedulerProvider = baseSchedulerProvider
    private var mView = view
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private var mTasksRepository = tasksRepository
    lateinit var disposable: Disposable

    init {
        mView.setPresenter(this)
    }

    override fun subscribe() {
        getAllTask()
    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }

    override fun getAllTask() {
        val disposable = mTasksRepository.getTasks()
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe { list ->
                    kotlin.run {
                        if (mView.isActive)  {
                            mView.showAllTask(list)
                        }
                    }
                }
        mCompositeDisposable.add(disposable)
    }

    override fun getUnDoTasks() {
        TODO("=======^^=========================^^=====")
    }

    override fun saveTask(task: Task) {
        val disposable = Completable.fromAction {
            mTasksRepository.saveTask(task)
        }
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe()
        mCompositeDisposable.add(disposable)
    }

    override fun deleteTaskByName(name: String) {
        val subscribe = Completable.fromAction {
            mTasksRepository.deleteTaskByName(name)
        }
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe()
        mCompositeDisposable.add(subscribe)
    }

    override fun deleteTaskById(taskId: Long) {
        val subscribe = Completable.fromAction {
            mTasksRepository.deleteTaskById(taskId)
        }
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe()
        mCompositeDisposable.add(subscribe)
    }
}