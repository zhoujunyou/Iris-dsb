package com.zjy.leanrxjava.appmanagers

import android.app.Application
import com.mwee.android.mweebase.base.GlobalCache
import com.zjy.leanrxjava.BuildConfig
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService
import timber.log.Timber
import javax.inject.Inject

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/5

 */
class AppManagers(vararg val managers: AppManager):AppManager{
    override fun init(application: Application) {
        managers.forEach {
            it.init(application)
        }
    }

}

/**
 * 日志工具 使用后的感受是不用每个库添加一个日志工具了 全用timber就可以搞定
 */
class TimberManager @Inject constructor():AppManager {
    override fun init(application: Application) {
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}

class GlobalCacheManager @Inject constructor() :AppManager{
    override fun init(application: Application) {
        GlobalCache.getInstance().registerContext(application)
    }

}


/**
 * http://www.jianshu.com/p/520a9055536f
 * 数据库实时调试工具
 */
class SQLiteStudioManager @Inject constructor():AppManager{
    override fun init(application: Application) {
        if(BuildConfig.DEBUG){
            SQLiteStudioService.instance().start(application)
        }
    }

}