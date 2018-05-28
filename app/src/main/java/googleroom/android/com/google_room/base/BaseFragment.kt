package googleroom.android.com.google_room.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *  Created By handan
 *  CreateDate: 2018/5/23
 *  Desc:
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(getLayoutId(), container, false)
        onCreateView()
        return inflate
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    abstract fun onCreateView()
    abstract fun getLayoutId(): Int
    abstract fun onViewCreated()
}