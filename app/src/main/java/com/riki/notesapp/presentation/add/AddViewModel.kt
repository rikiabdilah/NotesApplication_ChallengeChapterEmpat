package com.riki.notesapp.presentation.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.riki.notesapp.data.NoteDatabase
import com.riki.notesapp.data.NoteEntity
import kotlinx.coroutines.launch

class AddViewModel(app: Application) : AndroidViewModel(app) {

    fun add(noteEntity: NoteEntity) = viewModelScope.launch {
        NoteDatabase.getInstance((getApplication()))!!.noteDao().insertNote(noteEntity)
    }
}