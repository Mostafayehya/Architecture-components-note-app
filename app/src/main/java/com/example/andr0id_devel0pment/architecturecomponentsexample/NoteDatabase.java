package com.example.andr0id_devel0pment.architecturecomponentsexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    //to enable the database access the CRUD operations
    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(populateDbAsyncTask)
                    .build();
        }

        return instance;
    }

    public static RoomDatabase.Callback populateDbAsyncTask = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDataAsyncTask(instance).execute();
        }
    };

    public static class populateDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private static NoteDao noteDao;

        public populateDataAsyncTask(NoteDatabase db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("note 1", "description 1", 1));
            noteDao.insert(new Note("note 2", "description 2", 2));
            noteDao.insert(new Note("note 3", "description 3", 3));
            noteDao.insert(new Note("note 4", "description 4", 4));

            return null;
        }
    }

}
