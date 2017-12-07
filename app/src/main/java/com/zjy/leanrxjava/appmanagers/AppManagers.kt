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

class SQLiteStudioManager @Inject constructor():AppManager{
    override fun init(application: Application) {
        if(BuildConfig.DEBUG){
            SQLiteStudioService.instance().start(application)
        }
    }

}