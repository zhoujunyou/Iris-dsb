package com.zjy.data

import android.arch.persistence.room.Room
import android.content.Context
import com.zjy.data.login.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/7

 */
@Module
class DataBaseModule {
    /**
     * 用 const 标注的（在类中以及在顶层的）属性在 Java 中会成为静态字段
     */
    companion object {
        const val DB_NAME = "iris.db"
    }

    @Singleton
    @Provides
    fun provideDataBase(context: Context): IrisDataBase {
        return Room.databaseBuilder(context, IrisDataBase::class.java, DB_NAME).build()
    }

    @Provides
    fun provideUserDao(db:IrisDataBase):UserDao{
        return db.userDao()
    }
}