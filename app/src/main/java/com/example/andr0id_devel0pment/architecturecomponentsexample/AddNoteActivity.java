package com.example.andr0id_devel0pment.architecturecomponentsexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;


//TODO **************************************************************************
//TODO [*] build the layout of the add note activity
//TODO [*] add the functionality of the save button inside the addNoteActivity to send the note data back to the mainActivity
//TODO [*] implement the functionality of the back button of the AddNoteActivity
//TODO [*] implement the functionality of the numberPicker inside this activity
//TODO [] add The floating Action button functionality inside the MainActivity
//TODO [] Receive data back from the addNoteActivity
//TODO [] Save the into the NoteLiveData
//TODO [] adjust the main activity to be a single top and check the behaviour of the app without it, and set it as the parent of

public class AddNoteActivity extends AppCompatActivity {
    public static final String ACTIVITY_TITLE = "Add Note";
    public static final String EXTRA_KEY_TITLE = "com.example.andr0id_devel0pment.architecturecomponentsexample.title";
    public static final String EXTRA_KEY_DESCRIPTION = "com.example.andr0id_devel0pment.architecturecomponentsexample.description";
    public static final String EXTRA_KEY_PRIORITY = "com.example.andr0id_devel0pment.architecturecomponentsexample.priority";
    public static final int REQUEST_CODE = 1;

    EditText editTextTitle;
    EditText editTextDescription;
    NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //Setting up the Menu bar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle(ACTIVITY_TITLE);

        editTextTitle = findViewById(R.id.edit_text_add_title);
        editTextDescription = findViewById(R.id.edit_text_add_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);


    }

    public void saveNote() {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Note title and description can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent();
        intent.putExtra(EXTRA_KEY_TITLE, title);
        intent.putExtra(EXTRA_KEY_DESCRIPTION, description);
        intent.putExtra(EXTRA_KEY_PRIORITY, priority);

        //This is part of a technique used to send back data to the parent/start activity of this activity
        // (sending back data)
        startActivityForResult(intent, REQUEST_CODE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.add_note_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
