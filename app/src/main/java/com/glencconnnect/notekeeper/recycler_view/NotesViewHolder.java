/**
 * Created by glenc on Mar 2021
 **/

package com.glencconnnect.notekeeper.recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glencconnnect.notekeeper.R;

class NotesViewHolder  extends RecyclerView.ViewHolder {

    private final TextView noteItem;
    private final TextView noteSub;
    private final TextView noteDate;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        noteItem = itemView.findViewById(R.id.note_item);
        noteSub = itemView.findViewById(R.id.note_subtitle);
        noteDate = itemView.findViewById(R.id.note_date);
    }

    public void bind(String text, String subtitle, String date) {
        noteItem.setText(text);
        noteSub.setText(subtitle);
        noteDate.setText(date);
    }

    static NotesViewHolder create (ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_item,parent,false);
        return new NotesViewHolder(view);
    }

}


