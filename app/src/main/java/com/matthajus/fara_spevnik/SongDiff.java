package com.matthajus.fara_spevnik;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class SongDiff extends DiffUtil.ItemCallback<Song> {

    @Override
    public boolean areItemsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
        return Objects.equals(oldItem.getSongText(), newItem.getSongText()) && Objects.equals(oldItem.getSongName(), newItem.getSongName());
    }
}
