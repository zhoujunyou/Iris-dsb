package com.zjy.data.login

import android.arch.persistence.room.*
import io.reactivex.Flowable

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/7

 */
@Dao
interface UserDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertUser(user:User):Long

  @Query("SELECT * FROM user")
  fun getUsers():Flowable<List<User>>


  @Delete
  fun deleteUser(user: User)

}