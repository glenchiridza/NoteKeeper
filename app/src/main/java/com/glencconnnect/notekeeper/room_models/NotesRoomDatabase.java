/**
 * Created by glenc on Mar 2021
 **/

package com.glencconnnect.notekeeper.room_models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Notes.class}, version = 2, exportSchema = false)
public abstract class NotesRoomDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    private static volatile NotesRoomDatabase INSTANCE;

    //run on 4 threads
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static NotesRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (NotesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotesRoomDatabase.class, "notes_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;

    }

    //callback class to provide start values for the database when first created
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriterExecutor.execute(() ->{
                NotesDao dao = INSTANCE.notesDao();
                dao.deleteAll();

                Notes notes = new Notes("Just Learn RDBMS", "Room", "Room making db connection easier", "12/02/21");
                dao.insert(notes);
                notes = new Notes("Fundamentals of Database Design", "Room", "Room making db connection easier", "12/02/21");
                dao.insert(notes);
            });
        }
    };
}


