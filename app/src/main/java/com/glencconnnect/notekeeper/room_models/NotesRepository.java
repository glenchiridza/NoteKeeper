/**
 * Created by glenc on Mar 2021
 **/

package com.glencconnnect.notekeeper.room_models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepository {

    private NotesDao mNotesDao;
    private LiveData<List<Notes>> mAllNotes;

    public NotesRepository(Application application) {
        NotesRoomDatabase db = NotesRoomDatabase.getDatabase(application);
        mNotesDao = db.notesDao();
        mAllNotes = mNotesDao.getOrderedNotes();
    }

    //get all notes from dao
    public LiveData<List<Notes>> getAllNotes() {
        return mAllNotes;
    }

    //insert notes thru dao
    public void insert(Notes notes) {
        NotesRoomDatabase.databaseWriterExecutor.execute(()->{
            mNotesDao.insert(notes);
                });
    }

    //update
    public void update(Notes note){
        NotesRoomDatabase.databaseWriterExecutor.execute(()->{
            mNotesDao.update(note);
        });
    }

    //delete singular note
    public void deleteNote(Notes note){
        NotesRoomDatabase.databaseWriterExecutor.execute(()->{
            mNotesDao.deleteNote(note);
        });

    }

    //delete all noted via dao
    public void deleteAll (){
        NotesRoomDatabase.databaseWriterExecutor.execute(()->{
            mNotesDao.deleteAll();
        });
    }



}


