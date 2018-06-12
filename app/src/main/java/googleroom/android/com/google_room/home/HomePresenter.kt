package googleroom.android.com.google_room.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.maning.updatelibrary.InstallUtils
import googleroom.android.com.google_room.data.TaskDataSource
import googleroom.android.com.google_room.data.TasksRepository
import googleroom.android.com.google_room.data.bean.Task
import googleroom.android.com.google_room.file.FileOptions
import googleroom.android.com.google_room.data.TaskDataSource
import googleroom.android.com.google_room.data.TasksRepository
import googleroom.android.com.google_room.data.bean.Task
import googleroom.android.com.google_room.rxutil.BaseSchedulerProvider
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
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
                        if (mView.isActive) {
                            mView.showAllTask(list)
                        }
                    }
                }
        mCompositeDisposable.add(disposable)
    }

    override fun getUnDoTasks() {
        Observable.create(object : ObservableOnSubscribe<MutableList<Task>> {
            override fun subscribe(e: ObservableEmitter<MutableList<Task>>) {
                mTasksRepository.queryUnDoTasks(object : TaskDataSource.QueryTasksCallBack {
                    override fun onSuccess(taskList: MutableList<Task>) {
                        e.onNext(taskList)
                    }

                    override fun onFail(statusCode: Int, errorMsg: String) = Unit
                })
            }
        }).subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(object : Observer<MutableList<Task>> {
                    override fun onError(e: Throwable) {
                        if (!mView.isActive) return
                        mView.onError(e.hashCode(), e.message.toString())
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: MutableList<Task>) {
                        if (!mView.isActive) return
                        mView.showAllTask(t)
                    }

                    override fun onComplete() = Unit
                })
        mCompositeDisposable.add(disposable)
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

    override fun writeFile(context: Context, msg: String) {
        val subscribe = Completable.fromAction {
            FileOptions(context).writeFile(msg)
        }.subscribeOn(mBaseSchedulerProvider.io())
                .subscribe()
        mCompositeDisposable.add(subscribe)
    }

    override fun readFile(context: Context) {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>) {
                val readFile =
                        FileOptions(context).read()
                e.onNext(readFile)
            }
        }).subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: String) {
                        Log.d("=======>", "<========= $t")
                        mView.showText(t)
                    }

                    override fun onError(e: Throwable) = Unit
                })
        mCompositeDisposable.add(disposable)
    }

    //https://og3tpc3bg.qnssl.com/Android/Netfits_Android_2.7.0.4748.apk
    override fun updateApk(context: Context) {
        val url = "https://og3tpc3bg.qnssl.com/Android/Netfits_Android_2.7.0.4748.apk"
        // 浏览器下载
//        InstallUtils.installAPKWithBrower(context, url)

        InstallUtils.with(context)
                .setApkUrl("https://og3tpc3bg.qnssl.com/Android/Netfits_Android_2.7.0.4748.apk")
                .setApkName("Netfits_Android_2.7.0.4748")
                .setCallBack(object : InstallUtils.DownloadCallBack {
                    override fun onComplete(p0: String?) {
                        /**
                         * 安装APK工具类
                         * @param context       上下文
                         * @param filePath      文件路径
                         * @param callBack      安装界面成功调起的回调
                         */

                        InstallUtils.installAPK(context, p0, object : InstallUtils.InstallCallBack {
                            override fun onSuccess() {
                                Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show()
                            }

                            override fun onFail(e: Exception) {
                                Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show()
                            }
                        })
                    }

                    override fun onFail(p0: Exception?) {
                    }

                    override fun onLoading(p0: Long, p1: Long) {
                        mView.showProgress(p0, p1)
                    }

                    override fun onStart() {
                    }

                    override fun cancle() {
                    }
                })
                .startDownload()
    }
=======
>>>>>>> git/master
}