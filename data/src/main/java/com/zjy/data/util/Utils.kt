package com.zjy.data.util

import android.app.Application

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/11/8

 */
object Utils {
    private lateinit var app: Application
    @JvmStatic
    fun init(context: Application) {
        this.app = context
    }

    @JvmStatic
    fun context(): Application {
        return app
    }

}
