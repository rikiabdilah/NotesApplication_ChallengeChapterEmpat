package com.riki.notesapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "username")
    val username : String,
    @ColumnInfo(name = "email")
    val email : String,
    @ColumnInfo(name = "password")
    val password : String) : Parcelable