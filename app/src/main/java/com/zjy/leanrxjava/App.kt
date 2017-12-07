package com.zjy.leanrxjava

import com.zjy.leanrxjava.appmanagers.AppManagers
import com.zjy.leanrxjava.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/11/8

 */
class App: DaggerApplication() {
    @Inject lateinit var managers : AppManagers
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        managers.init(this)
    }
}