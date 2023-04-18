package com.riki.notesapp.presentation.delete

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riki.notesapp.data.NoteDatabase
import com.riki.notesapp.data.NoteEntity
import kotlinx.coroutines.launch

class DeleteViewModel(app: Application) : AndroidViewModel(app) {
    fun delete(noteEntity: NoteEntity) = viewModelScope.launch {
        NoteDatabase.getInstance((getApplication()))!!.noteDao().deleteNote(noteEntity)
    }
}