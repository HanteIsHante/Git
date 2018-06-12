package googleroom.android.com.google_room.data.localData

import android.content.Context
import googleroom.android.com.google_room.data.UserDataSource
import googleroom.android.com.google_room.data.bean.User
import io.reactivex.Flowable

/**
 *  Created By handan
 *  CreateDate: 2018/5/10
 *  Desc:
 */
class UserLocalDataSource(context: Context) : UserDataSource {

    private val userDao = ToDoDataSource.getInstance(context).userDao()

    override fun deleteUserById(userId: Long) {
        userDao.deleteUserById(userId)
    }

    override fun queryUserList(): Flowable<MutableList<User>> {
        return userDao.getUserList()
    }

    override fun updateUser(userId: Long, userInfo: User) {
        userDao.updateUserById(userId, userInfo.name)
    }

    override fun saveUser(user: User) {
        userDao.insertUserInfo(user)
    }


}