package com.zjy.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.zjy.data.login.User
import com.zjy.data.login.UserDao

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/7

 */
@Database(entities = [User::class], version = 2 )
abstract class IrisDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}