/**
 * Created by glenc on Mar 2021
 **/

package com.glencconnnect.notekeeper.recycler_view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.glencconnnect.notekeeper.room_models.Notes;

public class NotesListAdapter extends ListAdapter<Notes, NotesViewHolder> {


    public NotesListAdapter(@NonNull DiffUtil.ItemCallback<Notes> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return NotesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Notes current = getItem(position);
        holder.bind(current.getNotes(), current.getSubTitle(), current.getDate());
    }

    public Notes getNotePos(int pos){
        return getItem(pos);
    }

    public static class NotesDiff extends DiffUtil.ItemCallback<Notes> {

        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getNotes().equals(newItem.getNotes());
        }
    }
}


