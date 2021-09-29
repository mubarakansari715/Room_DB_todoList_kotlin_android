package com.example.todo

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel (app: Application): AndroidViewModel(app) {

    fun insert(context: Context, user: User) {
        UserRepository.insert(context, user)
    }

    fun delete(user: User) {
        UserRepository.delete(user)
    }

    suspend fun update(user: User) {
        val userDao = UserDatabase.getInstance(getApplication())?.userDao()
        userDao?.update(user)
        //UserRepository.update(user)
    }

    fun getAllUserData(context: Context): LiveData<List<User>> {
        return UserRepository.getAllUserData(context)
    }
}