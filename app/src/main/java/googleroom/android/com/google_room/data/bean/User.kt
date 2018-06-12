package googleroom.android.com.google_room.data.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *  Created By handan
 *  CreateDate: 2018/5/10
 *  Desc:
 */
@Entity(tableName = "users")
class User(@PrimaryKey @ColumnInfo(name = "id") var id: Long,
           @ColumnInfo(name = "name") var name: String,
           @ColumnInfo(name = "age") var age: Int,
           @ColumnInfo(name = "sex") var sex: String)