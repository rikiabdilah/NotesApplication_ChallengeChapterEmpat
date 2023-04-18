package com.riki.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.riki.notesapp.data.NoteDatabase
import com.riki.notesapp.data.UserEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UserViewModel (app : Application) : AndroidViewModel(app) {
    var allUser: MutableLiveData<List<UserEntity>>

    init {
        allUser = MutableLiveData()
    }

    fun verifyUser(email: String, password: String): LiveData<UserEntity> =
        NoteDatabase.getInstance((getApplication()))!!.userDao().verificatonUser(email, password)

    fun insertUser(user: UserEntity) {
        GlobalScope.async {
            val userDAO = NoteDatabase.getInstance(getApplication())?.userDao()!!
            userDAO.insertUser(user)
        }
    }
}