package googleroom.android.com.google_room.douban

import googleroom.android.com.google_room.BasePresenter
import googleroom.android.com.google_room.BaseView

/**
 *  Created By handan
 *  CreateDate: 2018/5/29
 *  Desc:
 */
interface DouBanContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean
    }

    interface Presenter : BasePresenter
}