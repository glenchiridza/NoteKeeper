/**
 * Created by glenc on Mar 2021
 **/

package com.glencconnnect.notekeeper.room_models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Update
    void update(Notes... note);

    @Delete
    void deleteNote(Notes note);

    @Query("DELETE FROM notes_table")
    void deleteAll();

    @Query("SELECT * FROM notes_table ORDER BY date ASC")
    LiveData<List<Notes>> getOrderedNotes();
}


