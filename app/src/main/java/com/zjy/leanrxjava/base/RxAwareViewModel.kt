package com.zjy.leanrxjava.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/5

 */
open class RxAwareViewModel : ViewModel() {
    val disposables = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}