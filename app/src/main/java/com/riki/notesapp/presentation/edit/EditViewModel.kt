package com.riki.notesapp.presentation.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riki.notesapp.data.NoteDatabase
import com.riki.notesapp.data.NoteEntity
import kotlinx.coroutines.launch

class EditViewModel(app: Application) : AndroidViewModel(app) {
    fun edit(noteEntity: NoteEntity) = viewModelScope.launch {
        NoteDatabase.getInstance((getApplication()))!!.noteDao().updateNote(noteEntity)
    }
}