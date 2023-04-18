package com.riki.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface UserDao {
    @Query("SELECT*FROM table_user WHERE email = :email AND password = :password")
    fun verificatonUser(email:String, password:String): LiveData<UserEntity>

    @Insert
    fun insertUser(user : UserEntity)
}