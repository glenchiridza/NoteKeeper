/**
 * Created by glenc on Mar 2021
 **/

package com.glencconnnect.notekeeper.room_models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes{
//    @PrimaryKey(autoGenerate = true)
//    private int id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="notes")
    private String mNotes;

    @NonNull
    @ColumnInfo(name = "subtitle")
    private String mSubTitle;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;

    public Notes(@NonNull String notes, @NonNull String mSubTitle, @NonNull String mDescription, @NonNull String mDate){
        this.mNotes = notes;
        this.mSubTitle = mSubTitle;
        this.mDescription = mDescription;
        this.mDate = mDate;
    }

    public String getNotes(){ return this.mNotes;}

    public String getSubTitle() {
        return mSubTitle;
    }


    public String getDescription() {
        return mDescription;
    }


    public String getDate() {
        return mDate;
    }
}


