package com.example.mvp_mvvm

import android.os.Handler
import androidx.annotation.MainThread


class LoginContract {

    interface View {
        @MainThread
        fun setSuccess()
        @MainThread
        fun setError(error: String)
        @MainThread
        fun showProgress()
        @MainThread
        fun hideProgress()
//        fun getHandler(): Handler
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanged()
    }

}