package com.example.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:User)

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAllUserData(): LiveData<List<User>>
}