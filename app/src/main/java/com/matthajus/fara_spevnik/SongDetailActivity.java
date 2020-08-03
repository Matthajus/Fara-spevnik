package com.matthajus.fara_spevnik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SongDetailActivity extends AppCompatActivity {

    TextView songName;
    EditText songText;
    SongViewModel songViewModel;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        songName = findViewById(R.id.detailSongName);
        songText = findViewById(R.id.detailSongText);
        floatingActionButton = findViewById(R.id.fab);

        Intent intent = getIntent();
        Song song = (Song) intent.getSerializableExtra("song");

        songName.setText(song.getSongName());
        songText.setText(song.getSongText());



        ViewModelProvider.AndroidViewModelFactory viewModelFactory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, viewModelFactory);
        songViewModel = viewModelProvider.get(SongViewModel.class);

        floatingActionButton.setOnClickListener(v ->{
            Toast.makeText(this,"Po reštarte aplikácie sa zmeny uložia" ,Toast.LENGTH_SHORT).show();
            Song updatedSong = new Song();
            updatedSong.setId(song.getId());
            updatedSong.setSongName(songName.getText().toString());
            updatedSong.setSongText(songText.getText().toString());

            System.out.println(updatedSong.getSongText());

            songViewModel.updateSong(updatedSong);

        });

    }
}