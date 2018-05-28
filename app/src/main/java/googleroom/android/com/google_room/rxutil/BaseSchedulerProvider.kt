package googleroom.android.com.google_room.rxutil

import io.reactivex.Scheduler

/**
 *  Created By handan
 *  CreateDate: 2018/5/11
 *  Desc:
 */
interface BaseSchedulerProvider {


    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun newThread(): Scheduler
}