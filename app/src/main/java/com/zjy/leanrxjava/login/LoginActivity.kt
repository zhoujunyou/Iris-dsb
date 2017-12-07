package com.zjy.leanrxjava.login

import android.os.Bundle
import com.zjy.leanrxjava.util.addFragmentToActivity
import dagger.android.support.DaggerAppCompatActivity


class LoginActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.findFragmentById(android.R.id.content)
                as LoginFragment? ?: LoginFragment().also {
            addFragmentToActivity(it, android.R.id.content)
        }

    }
}
