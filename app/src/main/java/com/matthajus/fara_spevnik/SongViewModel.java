package com.matthajus.fara_spevnik;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class SongViewModel extends AndroidViewModel {

    private LiveData<List<Song>> songs;

    private SongDao songDao;

    private AppDatabase db;

    public SongViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getDb(application);
        songDao = db.songDao();
        songs = songDao.getAll();
    }

    public LiveData<List<Song>> getSongs(){
        return songs;
    }

    public void addSong(Song song){
        db.getTransactionExecutor().execute(() -> songDao.addSong(song));
    }

    public void updateSong(Song song){
        db.getTransactionExecutor().execute(() -> songDao.updateSong(song));
    }
}
