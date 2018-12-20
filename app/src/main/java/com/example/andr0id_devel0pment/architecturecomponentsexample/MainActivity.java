package com.example.andr0id_devel0pment.architecturecomponentsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO(1)Access the below layer of the view which is ViewModel
        //TODO assign the ViewModel to this activity and specify which viewmodel for this activity
        //TODO Observe this data model to reflect the change in the data

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //building the recyclerView
    //TODO 1 create noteItem.xml
    //TODO 2 Edit the mainActivity.xml layout to fit a recyclerView
    //TODO 3 create the Adapter class that extends recycler View.adapter
    //TODO 4 connect the RecyclerView with the adapter inside the main activity class
    //TODO 5 inside the observer annymous inccer class of observer to change the data of the adapter whenever it changes

}
