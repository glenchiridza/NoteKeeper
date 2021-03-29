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

    public LiveData<List<Notes>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Notes notes) {
        NotesRoomDatabase.databaseWriterExecutor.execute(()->{
            mNotesDao.insert(notes);
                });
    }


}


