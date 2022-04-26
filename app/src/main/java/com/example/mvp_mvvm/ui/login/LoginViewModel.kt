package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.LoginUseCase
import com.example.mvp_mvvm.utils.Publisher

class LoginViewModel(private val loginUseCase: LoginUseCase
): LoginContract.ViewModel {
    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher(true)


    override fun onLogin(login: String, password: String) {

        shouldShowProgress.post(true)
        loginUseCase.login(login, password) { result ->
            shouldShowProgress.post(false)
            if(result) {
                isSuccess.post(true)
                errorText.post("")
            } else {
                isSuccess.post(false)
                errorText.post("Неверный пароль")
            }
        }
    }

    override fun onCredentialsChanged() {
        //todo
    }
}