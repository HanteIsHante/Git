package googleroom.android.com.google_room.data.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *  Created By handan
 *  CreateDate: 2018/6/4
 *  Desc:
 */
@Entity(tableName = "books")
class Book(@PrimaryKey @ColumnInfo(name = "id") var id: Long,
           @ColumnInfo(name = "bookName") var bookName: String,
           @ColumnInfo(name = "bookDesc") var bookDesc: String)