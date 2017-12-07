package com.zjy.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration



/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/7

 */
val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        /**
         * 升级后的表结构要和依赖Entity创建的表结构一样（包括是否为NULL)
         */
        database.execSQL("ALTER TABLE user ADD COLUMN vip INTEGER")
    }
}