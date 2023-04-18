package com.riki.notesapp.data

import android.content.Context
import android.content.SharedPreferences

/**
 * @Author: rikiabdilah
 * Github: https://github.com/rikiabdilah
 */

class NotePreferences(context: Context) {

    private var sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences("NOTE_PREFERENCES", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

}