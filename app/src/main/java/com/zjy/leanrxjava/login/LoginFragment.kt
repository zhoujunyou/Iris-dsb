package com.zjy.leanrxjava.login

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.zjy.leanrxjava.R
import com.zjy.leanrxjava.base.Response
import com.zjy.leanrxjava.extensions.observeK
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/11/3

 */
class LoginFragment
@Inject constructor() : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginFragmentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginFragmentModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(titleBar) {
            setTitle("登录", android.view.Gravity.CENTER)
            setTitleColor(android.graphics.Color.WHITE)
        }
        var btnLogin = view.findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener { viewModel.login() }
        RxTextView.textChanges(editUser).skip(1).subscribe(viewModel.userNameObsevable)
        RxTextView.textChanges(editPassword).skip(1).subscribe(viewModel.passwordObservable)
        viewModel.loadUser()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.user.observeK(this){
            it?.let {
                editUser.setText(it.userName)
                editPassword.setText(it.password)
            }
        }
        viewModel.response.observeK(this) {
            when (it?.status) {
                Response.Status.SUCCESS -> {

                }
                Response.Status.ERROR -> {
                    Toast.makeText(this.context, it.error?.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.loadStatus.observeK(this) {

        }

        viewModel.loginBtnStatus.observeK(this){
            it?.let {
                btnLogin.isEnabled=it
                /**
                 * java 中a ? b : c  等价于 kotlin if (a) b else c
                 */
                btnLogin.setBackgroundColor(if(it) Color.RED else Color.GRAY )
            }
        }

    }


}