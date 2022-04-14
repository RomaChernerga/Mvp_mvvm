package com.example.mvp_mvvm.domain

import com.example.mvp_mvvm.domain.entities.UserProfile

interface UserRepo {

    //CRUD

    //Create
    fun addUser(user: UserProfile)

    //Read
    fun getAllUsers() : List<UserProfile>

    //Update
    fun changeUser(id:String,  user: UserProfile)

    //Delete
    fun deleteUser(id: String)
    fun deleteAll()
}