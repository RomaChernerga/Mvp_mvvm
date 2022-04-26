package com.example.mvp_mvvm.ui.login

import androidx.annotation.MainThread
import com.example.mvp_mvvm.utils.Publisher


interface LoginContract {
    /**
     * MVP -  Model View Presenter
     *      проблемы
     * 1)Восстановление состояния
     * 2)Большая связность
     * 3) Большое клличество кода, проверка на null, постоянные вызовы view
     * (M) <- (P) <-> (V)
     *
     * MVVM - Model View ViewModel ((M) <- (VM) <- (V) - Нужно сделать так)
     */

        // Боллее View нам не нужен и в MVVM его роль достается подпискам во ViewModel
//    interface View {
//        @MainThread
//        fun setSuccess()
//
//        @MainThread
//        fun setError(error: String)
//
//        @MainThread
//        fun showProgress()   //shouldShowProgress
//
//        @MainThread
//        fun hideProgress()   //shouldShowProgress
//    }

    /**
     * class Activity {
     *
     * fun onCreate() {
     *      viewModel.shouldShowProgress.subscribe { it ->
     *         if(it) {
     *              dialog.show()
     *         } else {
     *         dialog.dismiss()
     *         }
     *      }
     * }
     *
     * }
     */
    interface ViewModel {
        val shouldShowProgress: Publisher <Boolean>
        val isSuccess: Publisher <Boolean>
        val errorText: Publisher <String?>

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onCredentialsChanged()
    }

}