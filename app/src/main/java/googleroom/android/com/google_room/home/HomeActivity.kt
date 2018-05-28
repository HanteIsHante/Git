package googleroom.android.com.google_room.home

import googleroom.android.com.google_room.R
import googleroom.android.com.google_room.base.BaseActivity
import googleroom.android.com.google_room.data.TasksRepository
import googleroom.android.com.google_room.data.localData.TaskLocalDataSource
import googleroom.android.com.google_room.data.remotedata.TaskRemoteDataSource
import googleroom.android.com.google_room.rxutil.SchedulerProvider
import googleroom.android.com.google_room.util.ActivityUtils

/**
 *  Created By handan
 *  CreateDate: 2018/5/10
 *  Desc:
 */
class HomeActivity : BaseActivity() {

    override fun onCreateInit() {
        var homeFragment: HomeFragment? =
                supportFragmentManager.findFragmentById(R.id.home_fragment_layout) as? HomeFragment
        if (homeFragment == null) {
            homeFragment = HomeFragment()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, homeFragment, R.id.home_fragment_layout)
        }
        val tasksRepository = TasksRepository.getInstance(TaskLocalDataSource.getInstance(this),
                TaskRemoteDataSource.getInstance())
        val schedulerProvider = SchedulerProvider.getInstance()
        HomePresenter(tasksRepository,
                schedulerProvider,
                homeFragment)
    }

    override fun getLayoutId(): Int = R.layout.home_layout

}

