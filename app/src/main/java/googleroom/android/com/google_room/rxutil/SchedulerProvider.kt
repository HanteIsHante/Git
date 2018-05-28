package googleroom.android.com.google_room.rxutil

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  Created By handan
 *  CreateDate: 2018/5/11
 *  Desc:
 */
class SchedulerProvider : BaseSchedulerProvider {
    /**
     * 每次总是开启新线程
     */
    override fun newThread(): Scheduler = Schedulers.newThread()

    /**
     * 计算所使用的Scheduler。
     * 这个计算是指CPU密集型计算，即不会被I/O等操作限制性的操作，
     * 例如图形的计算。这个Scheduler使用的固定的线程池，大小为cpu核数。
     * 不要把I/O放在computation中，否则I/O操作等待时间会浪费cpu。用于计算任务，
     * 如事件循环或和回调处理，不要用于IO操作(IO操作请使用Schedulers.io())；
     * 默认线程数等于处理器的数量
     */
    override fun computation(): Scheduler = Schedulers.computation()

    /**
     * I/O操作(读写文件、读写数据库、网络信息交互等)所使用的Scheduler，
     * 行为模式和newThread()差不多，区别在于io()的内部实现是用一个无数量上限的线程池，
     * 可以重用空闲的线程，因此多数情况下，io()比newThread()更有效率。
     * 不要把计算工作放在io(),可以避免穿件不必要的线程。
     */
    override fun io(): Scheduler = Schedulers.io()

    /**
     * 在RxAndroid中，他指定操作将在Android主线程中执行。
     */
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    companion object {
        private var INSTANCE: SchedulerProvider? = null
        private var lock = Any()


        fun getInstance(): SchedulerProvider {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = SchedulerProvider()
                }
            }
            return INSTANCE!!
        }
    }
}