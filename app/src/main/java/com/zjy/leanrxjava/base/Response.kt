package com.zjy.leanrxjava.base

/**

 *Description: Response holder provided to the UI
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/6

 */
data class Response<T>(var status: Status, var data: T, var error: Throwable?) {
    /**
     * Status of a response provided to the UI
     */
    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Response<T> {
            return Response<T>(Status.SUCCESS, data, null)
        }

        fun <T>error(error: Throwable,data:T): Response<T> {
            return Response(Status.ERROR, data , error)
        }
    }

}