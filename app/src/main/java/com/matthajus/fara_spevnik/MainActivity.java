package com.matthajus.fara_spevnik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.text.Collator;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements SongOnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private SongListAdapter songListAdapter;
    private SongViewModel songViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        songListAdapter = new SongListAdapter(this);
        recyclerView.setAdapter(songListAdapter);

        ViewModelProvider.AndroidViewModelFactory viewModelFactory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, viewModelFactory);
        songViewModel = viewModelProvider.get(SongViewModel.class);

        songViewModel.getSongs().observe(this, songs -> songListAdapter.submitList(songs));

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart)
            writeData();
    }

    public static void sortStrings(List<Song> songList) {
        Collator collator = Collator.getInstance(new Locale("sk", "SK"));
        Song tmp;
        for (int i = 0; i < songList.size(); i++) {
            for (int j = i + 1; j < songList.size(); j++) {
                if (collator.compare(songList.get(i).getSongName(), songList.get(j).getSongName()) > 0) {
                    tmp = songList.get(i);
                    songList.set(i, songList.get(j));
                    songList.set(j, tmp);
                }
            }
        }
    }

    @Override
    public void onSongClick(Song song) {
        Intent intent = new Intent(this, SongDetailActivity.class);
        intent.putExtra("song", song);
        startActivity(intent);
    }

    public void writeData() {
        List<Song> list = Data.loadData();
        sortStrings(list);
        for (Song song : list)
            songViewModel.addSong(song);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_tone:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_heart:
                Intent intent2 = new Intent(this, SvadbaActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_plus:
                Intent intent3 = new Intent(this, MolebenActivity.class);
                startActivity(intent3);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}