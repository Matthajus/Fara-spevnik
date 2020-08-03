package com.matthajus.fara_spevnik;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongViewHolder extends RecyclerView.ViewHolder {

    TextView text;

    public SongViewHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(android.R.id.text1);
    }

    @SuppressLint("SetTextI18n")
    public void bind(final Song song, final SongOnClickListener songOnClickListener) {
        text.setText(song.getSongName());
        text.setOnClickListener(v -> songOnClickListener.onSongClick(song));
    }
}
