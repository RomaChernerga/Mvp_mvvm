package com.example.mvp_mvvm.domain

import androidx.annotation.WorkerThread

interface LoginAPI {
    @WorkerThread
    fun login(login: String, password: String): Boolean

    @WorkerThread
    fun register(login: String, password: String, email: String): Boolean

    @WorkerThread
    fun logout(): Boolean

    @WorkerThread
    fun forgotPassword(login: String): Boolean

}