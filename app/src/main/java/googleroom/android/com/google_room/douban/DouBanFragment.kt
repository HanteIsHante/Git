package googleroom.android.com.google_room.douban

import googleroom.android.com.google_room.R
import googleroom.android.com.google_room.base.BaseFragment

/**
 *  Created By handan
 *  CreateDate: 2018/5/29
 *  Desc:
 */
class DouBanFragment : BaseFragment(), DouBanContract.View {
    override fun showBooks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mPresenter: DouBanContract.Presenter

    override fun onCreateView() = Unit

    override fun getLayoutId(): Int = R.layout.douban_layout

    override fun onViewCreated() {}



    override var isActive: Boolean = false
        get() = isAdded

    override fun setPresenter(presenter: DouBanContract.Presenter) {

        mPresenter = presenter

    }
}