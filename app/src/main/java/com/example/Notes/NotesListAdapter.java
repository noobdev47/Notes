package com.example.Notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {

    private List<Note> noteList;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.note_title);
            description = itemView.findViewById(R.id.note_description);
            date = itemView.findViewById(R.id.note_date);
        }
    }

    NotesListAdapter(List<Note> notesList) {
        noteList = notesList;
    }

    @Override
    public NotesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View noteView = inflater.inflate(R.layout.listviewactivity, parent, false);

        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        Note note = noteList.get(viewHolder.getAdapterPosition());

        TextView titleView = viewHolder.title;
        TextView descView = viewHolder.description;
        TextView dateView = viewHolder.date;
        titleView.setText(note.getTitle());
        descView.setText(note.getDescription());
        dateView.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}