package com.zjy.leanrxjava.appmanagers

import android.app.Application

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/5

 */
interface AppManager {
    fun init(application: Application)
}