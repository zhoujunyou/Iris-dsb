package com.zjy.leanrxjava.di

import android.app.Application
import com.zjy.leanrxjava.App
import com.zjy.leanrxjava.login.LoginBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    LoginBuilder::class,
    ViewModelBuilder::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface  Builder {

        @BindsInstance
         fun application(application: Application): AppComponent.Builder

         fun build():AppComponent
    }
}
