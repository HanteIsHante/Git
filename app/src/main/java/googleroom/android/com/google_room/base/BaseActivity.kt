package googleroom.android.com.google_room.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *  Created By handan
 *  CreateDate: 2018/5/16
 *  Desc:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onCreateInit()
    }

    abstract fun onCreateInit()
    abstract fun getLayoutId(): Int
}