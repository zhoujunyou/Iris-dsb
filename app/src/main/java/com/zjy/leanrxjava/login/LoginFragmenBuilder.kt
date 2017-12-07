package com.zjy.leanrxjava.login

import android.arch.lifecycle.ViewModel
import com.zjy.leanrxjava.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/5

 */
@Module
abstract class LoginFragmenBuilder {
    @ContributesAndroidInjector
    internal abstract fun loginFragment():LoginFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentModel::class)
    internal abstract fun bindLoginFragmentModel(viewModel: LoginFragmentModel):ViewModel

}