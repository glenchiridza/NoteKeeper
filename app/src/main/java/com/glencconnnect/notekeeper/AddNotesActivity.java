package com.glencconnnect.notekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glencconnnect.notekeeper.fragments.DatePickerFragment;
import com.glencconnnect.notekeeper.room_models.Notes;

import java.text.SimpleDateFormat;

public class AddNotesActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.glencconnect.notes_add";
    public static final String SUB = "com.glencconnect.notes_sub";
    public static final String DESC = "com.glencconnect.notes_desc";
    public static final String DATE = "com.glencconnect.notes_date";
    private EditText editNote;
    private EditText editSub;
    private EditText editDesc;
    private EditText editDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        editNote = findViewById(R.id.edit_note);
        editSub = findViewById(R.id.edit_subtitle);
        editDesc = findViewById(R.id.edit_description);
        editDate = findViewById(R.id.edit_date);

        //button save
        final Button btnSave = findViewById(R.id.button_save);
        btnSave.setOnClickListener(view ->{
            Intent intent = new Intent();
            if (TextUtils.isEmpty(editNote.getText())) {
                setResult(RESULT_CANCELED, intent);
            }else {
                String note = editNote.getText().toString();
                String subtile = editSub.getText().toString();
                String desc = editDesc.getText().toString();
                String date = editDate.getText().toString();

                intent.putExtra(EXTRA_REPLY, note);
                intent.putExtra(SUB, subtile);
                intent.putExtra(DESC, desc);
                intent.putExtra(DATE, date);
                setResult(RESULT_OK,intent);
            }
            finish();
        });

        //button cancel
        final Button btnCancel = findViewById(R.id.button_cancel);
        btnCancel.setOnClickListener(view ->{
            Toast.makeText(this, "note cancelled!", Toast.LENGTH_SHORT).show();
            finish();
        });


        //edit date
        editDate.setOnClickListener(view ->{
            DialogFragment dateFragment = new DatePickerFragment();
            dateFragment.show(getSupportFragmentManager(),getString(R.string.date_picker));

        });

    }

    public void processedDate(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String fullDate = (month_string +"/" + day_string +"/"+year_string);

        editDate.setText(fullDate);
    }
}