package googleroom.android.com.google_room.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import googleroom.android.com.google_room.data.bean.User
import io.reactivex.Flowable

/**
 *  Created By handan
 *  CreateDate: 2018/5/10
 *  Desc:
 */
@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(user: User)

    @Query("DELETE FROM users WHERE id=:id")
    fun deleteUserById(id: Long)

    @Query("SELECT * FROM users")
    fun getUserList(): Flowable<MutableList<User>>

    @Query("UPDATE users SET name=:userName WHERE id =:userId")
    fun updateUserById(userId: Long, userName: String)

    @Query("SELECT * FROM users WHERE id=:userId AND name=:userName")
    fun getAssignUser(userId: Long, userName: String): Flowable<User>


}