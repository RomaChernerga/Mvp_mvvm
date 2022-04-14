package com.example.mvp_mvvm.ui.login

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.mvp_mvvm.domain.LoginAPI
import com.example.mvp_mvvm.data.WebLoginApiImpl
import com.example.mvp_mvvm.domain.LoginUseCase

class LoginPresenter(private val loginUseCase: LoginUseCase
): LoginContract.Presenter {

    private var view: LoginContract.View? = null
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if(isSuccess) {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()

        loginUseCase.login(login, password) { result ->
            view?.hideProgress()
            if(result) {
                view?.setSuccess()
                isSuccess = true
                errorText = ""
            } else {
                view?.setError("Неверный пароль!!")
                isSuccess = false
                errorText = "Неверный пароль"
            }
        }
    }

    override fun onCredentialsChanged() {
        //todo
    }
}