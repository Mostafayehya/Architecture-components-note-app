package com.example.andr0id_devel0pment.architecturecomponentsexample;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> notesAsLiveData;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository=new NoteRepository(application);
        notesAsLiveData = noteRepository.getAllNotes();
    }

    public void insert(Note note){
        noteRepository.insert(note);
    }

    public void delete(Note note){
        noteRepository.delete(note);
    }

    public void update(Note note){
        noteRepository.update(note);
    }

    public void deleteAllNotes(){
        noteRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return notesAsLiveData;
    }
 }
