package com.riki.notesapp.presentation.home

import android.app.Application
import androidx.lifecycle.*
import com.riki.notesapp.data.NoteDatabase
import com.riki.notesapp.data.NoteEntity
import kotlinx.coroutines.launch

class HomeViewModel(
    app: Application
) : AndroidViewModel(app) {
    private val _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>> get() = _notes

    fun getAllNotesDesc() = viewModelScope.launch {
        _notes.postValue(NoteDatabase.getInstance((getApplication()))!!.noteDao().getAllNotesDesc())
    }

    fun getAllNotesAsc() = viewModelScope.launch {
        _notes.postValue(NoteDatabase.getInstance((getApplication()))!!.noteDao().getAllNotesAsc())
    }

    fun getAllNotes() = viewModelScope.launch {
        _notes.postValue(NoteDatabase.getInstance((getApplication()))!!.noteDao().getAllNotes())
    }
}