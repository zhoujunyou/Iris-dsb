package com.zjy.leanrxjava.di

import android.app.Application
import android.content.Context
import cn.mwee.android.pay.bsidedata.config.AbstractConfigProvider
import cn.mwee.android.pay.bsidedata.config.BSideDataConfig
import cn.mwee.android.pay.bsidedata.source.BSideDataSource
import com.zjy.leanrxjava.appmanagers.AppManagers
import com.zjy.leanrxjava.appmanagers.GlobalCacheManager
import com.zjy.leanrxjava.appmanagers.SQLiteStudioManager
import com.zjy.leanrxjava.appmanagers.TimberManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class ApplicationModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideAppManagers(timberManager: TimberManager
                           ,globalManagers: GlobalCacheManager
                           ,sqLiteStudioManager: SQLiteStudioManager): AppManagers {
        return AppManagers(timberManager,globalManagers,sqLiteStudioManager)
    }

    @Singleton
    @Provides
    fun  provideBsideDataSource(context: Context):BSideDataSource{
         BSideDataConfig.init(object : AbstractConfigProvider(){
             override fun getContext(): Context {
                 return context
             }

         })
        return BSideDataConfig.provideRepository()
    }

}

