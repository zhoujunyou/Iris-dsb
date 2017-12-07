package com.zjy.leanrxjava.login

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/5

 */
@Module
internal abstract class LoginBuilder {
    @ContributesAndroidInjector(modules = [
        LoginFragmenBuilder::class
    ])
    internal  abstract fun loginActivity() : LoginActivity
}