package com.example.mvp_mvvm.data

import android.os.Handler
import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.LoginAPI
import com.example.mvp_mvvm.domain.LoginUseCase

class LoginUseCaseImpl(
    // нам для раюоты нужны api и uiHandler
    private val api : LoginAPI
): LoginUseCase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = api.login(login, password)
            callback(result)

        }.start()
    }
}