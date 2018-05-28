package googleroom.android.com.google_room.data.localData

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import googleroom.android.com.google_room.data.bean.Task
import googleroom.android.com.google_room.data.bean.User
import googleroom.android.com.google_room.data.dao.TaskDao
import googleroom.android.com.google_room.data.dao.UserDao

/**
 *  Created By handan
 *  CreateDate: 2018/5/9
 *  Desc:
 */
@Database(entities = [(Task::class), (User::class)], version = 4)
abstract class ToDoDataSource : RoomDatabase() {


    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: ToDoDataSource? = null
        private val lock = Any()
        fun getInstance(context: Context): ToDoDataSource {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ToDoDataSource::class.java, "Android-P.db")
                            .addMigrations(migration1_2, migration2_3, migration3_4) // 用来进行数据库迁移
                            .build()
                }
                return INSTANCE!!
            }
        }

        /**
         * 用来数据迁移
         */
        private var migration1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) = Unit
        }
        /**
         * 数据表发生变化时
         */
        private var migration2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'tasks' ADD COLUMN 'last_update' INTEGER NOT NULL DEFAULT 0")
            }
        }

        /**
         * 新增数据库表
         */
        private var migration3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE users (id INTEGER ,name TEXT, age INTEGER, sex TEXT, PRIMARY KEY(id))")
            }
        }
    }
}