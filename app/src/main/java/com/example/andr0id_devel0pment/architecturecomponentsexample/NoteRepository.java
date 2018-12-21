package com.example.andr0id_devel0pment.architecturecomponentsexample;


import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

//TODO find more convienet way to write those Asynctasks because somebody on the comment sections on youtube said that
//TODO those Async Tasks can get Spagitii very fast


public class NoteRepository {

    private NoteDao noteDao;
    LiveData<List<Note>> allNotes;

    public NoteRepository(Application context) {
        NoteDatabase database = NoteDatabase.getInstance(context);
        this.noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new insertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new updateAsyncTask(noteDao).execute(note);


    }

    public void delete(Note note) {
        new deleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new deleteAllAsyncTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        public insertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;


        public deleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;


        public updateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;


        public deleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
