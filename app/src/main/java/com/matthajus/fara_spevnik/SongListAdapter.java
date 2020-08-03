package com.matthajus.fara_spevnik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

public class SongListAdapter extends ListAdapter<Song, SongViewHolder> {

    private SongOnClickListener songOnClickListener;

    public SongListAdapter(SongOnClickListener songOnClickListener) {
        super(new SongDiff());
        this.songOnClickListener = songOnClickListener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @LayoutRes int layout = android.R.layout.simple_list_item_1;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.bind(getItem(position), songOnClickListener);
    }
}
