package com.example.notetakingapp.respository

import com.example.notetakingapp.database.NoteDatabase
import com.example.notetakingapp.model.Note

class NoteRepository(val noteDB:NoteDatabase) {

    //we can also write this function as
    /*suspend fun insertNote2(note:Note): Unit{

        return noteDB.getNoteDAO().insertNote(note)
    }*/
    suspend fun insertNote(note:Note) = noteDB.getNoteDAO().insertNote(note)
    suspend fun updateNote(note:Note) = noteDB.getNoteDAO().updateNote(note)
    suspend fun deleteNote(note:Note) = noteDB.getNoteDAO().deleteNote(note)

    fun getAllNotes() = noteDB.getNoteDAO().getAllNotes()
    fun searchNote(searchText:String?) = noteDB.getNoteDAO().searchNote(searchText)



}