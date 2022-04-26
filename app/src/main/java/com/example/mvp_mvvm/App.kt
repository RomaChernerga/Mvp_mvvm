package com.example.mvp_mvvm

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.mvp_mvvm.data.LoginUseCaseImpl
import com.example.mvp_mvvm.data.api.MockLoginApiImpl
import com.example.mvp_mvvm.domain.LoginAPI
import com.example.mvp_mvvm.domain.LoginUseCase

class App: Application() {
    private val loginApi: LoginAPI by lazy { MockLoginApiImpl() }
    val loginUseCase: LoginUseCase by lazy {
        LoginUseCaseImpl(app.loginApi)
    }

}

val Context.app: App
    get() {
        return applicationContext as App
    }