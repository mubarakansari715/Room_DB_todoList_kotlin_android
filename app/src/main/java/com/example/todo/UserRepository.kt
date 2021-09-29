package com.example.todo

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository {

    companion object{
        var userDatabase:UserDatabase?=null

        private fun intialiseDB(context: Context): UserDatabase?
        {
            return UserDatabase.getInstance(context)!!
        }

        fun insert(context: Context, user:User)
        {
            userDatabase= intialiseDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.userDao().insert(user)
            }
        }

        fun getAllUserData(context: Context): LiveData<List<User>>
        {
            userDatabase= intialiseDB(context)
            return userDatabase!!.userDao().getAllUserData()
        }
    }
}