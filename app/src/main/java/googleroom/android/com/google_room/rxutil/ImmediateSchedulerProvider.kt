package googleroom.android.com.google_room.rxutil

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 *  Created By handan
 *  CreateDate: 2018/5/11
 *  Desc: 主要 用来写测试用例时使用
 */
class ImmediateSchedulerProvider : BaseSchedulerProvider {


    override fun newThread(): Scheduler = Schedulers.newThread()

    /**
     * Schedulers.trampoline()
     * 当其它排队的任务完成后，在当前线程排队开始执行
     */
    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()
}