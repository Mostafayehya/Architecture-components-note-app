package com.example.andr0id_devel0pment.architecturecomponentsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

//setting up viewModel
//TODO(1)Access the below layer of the view which is ViewModel
//TODO assign the ViewModel to this activity and specify which viewmodel for this activity
//TODO Observe this data model to reflect the change in the data


//building the recyclerView
//TODO 1 create noteItem.xml [x]
//TODO 2 Edit the mainActivity.xml layout to fit a recyclerView                                                          [x]
//TODO 3 create the Adapter class that extends recycler View.adapter                                                     [x]
//TODO 4 connect the RecyclerView with the adapter inside the main activity class                                        [x]
//TODO 5 inside the observer annymous incner class of observer to change the data of the adapter whenever it changes     [x]


public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private RecyclerView notesRecyclerView;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesRecyclerView=findViewById(R.id.recycler_view_note_list);

        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecyclerView.setHasFixedSize(true);

        noteAdapter = new NoteAdapter();
        notesRecyclerView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setNoteList(notes);
            }
        });
    }


}
