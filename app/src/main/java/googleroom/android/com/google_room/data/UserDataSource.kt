package googleroom.android.com.google_room.data

import googleroom.android.com.google_room.data.bean.User
import io.reactivex.Flowable

/**
 *  Created By handan
 *  CreateDate: 2018/5/10
 *  Desc:
 */
interface UserDataSource {

    fun saveUser(user: User)

    fun deleteUserById(userId: Long)

    fun queryUserList(): Flowable<MutableList<User>>

    fun updateUser(userId: Long, userInfo: User)


}