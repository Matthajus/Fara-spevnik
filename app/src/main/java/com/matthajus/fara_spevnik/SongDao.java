package com.matthajus.fara_spevnik;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SongDao {

    @Query("SELECT * FROM songs")
    LiveData<List<Song>> getAll();

    @Insert
    void addSong(Song song);

    @Update
    void updateSong(Song song);
}
