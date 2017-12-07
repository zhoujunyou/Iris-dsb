package com.zjy.leanrxjava.login

import android.arch.lifecycle.MutableLiveData
import cn.mwee.android.pay.bsidedata.model.login.request.LoginOptions
import cn.mwee.android.pay.bsidedata.source.BSideDataSource
import com.zjy.leanrxjava.base.Response
import com.zjy.leanrxjava.base.RxAwareViewModel
import com.zjy.leanrxjava.extensions.plusAssign
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/5

 */
internal class LoginFragmentModel @Inject constructor(val bsidedata: BSideDataSource) : RxAwareViewModel() {


    val response = MutableLiveData<Response<Boolean>>()

    val loadStatus = MutableLiveData<Boolean>()

    val loginBtnStatus = MutableLiveData<Boolean>()


    /**
     * !! 操作符

    非空断言运算符（!!）将任何值转换为非空类型，若该值为空则抛出异常。
    我们可以写 b!! ，这会返回一个非空的 b 值  如果 b 为空，就会抛出一个 NPE 异常：
     */
    val userName = PublishSubject.create<CharSequence>()!!

    /**
     * 声明一个属性的完整语法是

    var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]
     */
    val password = PublishSubject.create<CharSequence>()!!

    /**
     * 主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中：
     */
    init {
        disposables += Observable.combineLatest(
                userName, password, BiFunction<CharSequence, CharSequence, Boolean> { user, password ->
            user.isNotEmpty() && password.isNotEmpty()
        }).subscribe({
            loginBtnStatus.value=it
        })
    }


    fun login() {
        disposables += bsidedata.login(
                LoginOptions.Builder().build())
                .doOnSubscribe { loadStatus.value = true }
                .doAfterTerminate { loadStatus.value = false }
                .subscribe({
                    response.value = Response.success(true)
                }, { throwable ->
                    response.value = Response.error(throwable, false)
                })
    }

}