package com.zjy.data.login

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/5

 */
@Entity(tableName = "user")
data class User constructor(
        @ColumnInfo(name = "user_name")
        @PrimaryKey
        var userName: String="",

        var password: String="")