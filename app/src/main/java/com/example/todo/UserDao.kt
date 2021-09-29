package com.example.todo

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)


    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAllUserData(): LiveData<List<User>>
}