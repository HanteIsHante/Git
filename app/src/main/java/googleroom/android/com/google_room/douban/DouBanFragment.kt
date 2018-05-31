package googleroom.android.com.google_room.douban

import googleroom.android.com.google_room.base.BaseFragment

/**
 *  Created By handan
 *  CreateDate: 2018/5/29
 *  Desc:
 */
class DouBanFragment : BaseFragment(), DouBanContract.View {
    override fun onCreateView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewCreated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var isActive: Boolean = false
        get() = isAdded

    override fun setPresenter(presenter: DouBanContract.Presenter) {

    }
}