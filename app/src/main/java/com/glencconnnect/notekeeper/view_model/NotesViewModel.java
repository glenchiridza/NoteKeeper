/**
 * Created by glenc on Mar 2021
 **/

package com.glencconnnect.notekeeper.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.glencconnnect.notekeeper.room_models.Notes;
import com.glencconnnect.notekeeper.room_models.NotesRepository;

import java.util.List;


//sits as an intermediary between repository of data and the UI (MainActivity)

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository mRepository;
    private final LiveData<List<Notes>> mAllNotes;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NotesRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes(){return mAllNotes;}

    public void insert(Notes notes){mRepository.insert(notes);}

    public void delete(Notes note){mRepository.deleteNote(note);}

    public void deleteAll(){mRepository.deleteAll();}
}


