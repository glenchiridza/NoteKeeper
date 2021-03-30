package com.glencconnnect.notekeeper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.glencconnnect.notekeeper.recycler_view.NotesListAdapter;
import com.glencconnnect.notekeeper.room_models.Notes;
import com.glencconnnect.notekeeper.view_model.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private NotesViewModel mViewModel;
    public static final int NEW_NOTE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NotesListAdapter adapter = new NotesListAdapter(new NotesListAdapter.NotesDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NotesViewModel.class);

        mViewModel.getAllNotes().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
            startActivityForResult(intent, NEW_NOTE);
        });

        //itemtouchhelper for swipe
        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int pos = viewHolder.getAdapterPosition();
                        Notes note = adapter.getNotePos(pos);
                        Toast.makeText(MainActivity.this, "Deleting note", Toast.LENGTH_SHORT).show();

                        mViewModel.deleteNote(note);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_NOTE && resultCode ==RESULT_OK){
            Notes notes = new Notes(data.getStringExtra(AddNotesActivity.EXTRA_REPLY),
                    data.getStringExtra(AddNotesActivity.SUB),
                    data.getStringExtra(AddNotesActivity.DESC),
                    data.getStringExtra(AddNotesActivity.DATE));
            mViewModel.insert(notes);
        }else{
            Toast.makeText(getApplicationContext(), "note wasn't saved, did you do it right?", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_clear_all){
            Toast.makeText(this, "Notes deleted", Toast.LENGTH_SHORT).show();

            //delete the data
            mViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}