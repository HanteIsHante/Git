package googleroom.android.com.google_room.data.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *  Created By handan
 *  CreateDate: 2018/5/9
 *  Desc:
 */

@Entity(tableName = "tasks")
class Task(@PrimaryKey @ColumnInfo(name = "id") var id: Long,
           @ColumnInfo(name = "taskName") var taskName: String,
           @ColumnInfo(name = "taskTime") var taskTime: Long,
           @ColumnInfo(name = "isCompleted") var isCompleted: Boolean,
           @ColumnInfo(name = "last_update") var lastUpdate: Long)