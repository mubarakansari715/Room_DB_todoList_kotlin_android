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

        //insert data
        fun insert(context: Context, user:User)
        {
            userDatabase= intialiseDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.userDao().insert(user)
            }
        }

        //delete
        fun delete(user: User){
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.userDao().delete(user)
            }
        }

        //update data
        fun update(user:User)
        {
//            userDatabase= intialiseDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.userDao().update(user)
            }
        }

        //show all data
        fun getAllUserData(context: Context): LiveData<List<User>>
        {
            userDatabase= intialiseDB(context)
            return userDatabase!!.userDao().getAllUserData()
        }
    }
}