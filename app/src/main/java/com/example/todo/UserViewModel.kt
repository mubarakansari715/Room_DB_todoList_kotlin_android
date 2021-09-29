package com.example.todo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel  : ViewModel() {

    fun insert(context: Context, user: User) {
        UserRepository.insert(context, user)
    }

    fun delete(user: User) {
        UserRepository.delete(user)
    }

    fun update(user: User) {
        UserRepository.update(user)
    }

    fun getAllUserData(context: Context): LiveData<List<User>> {
        return UserRepository.getAllUserData(context)
    }
}