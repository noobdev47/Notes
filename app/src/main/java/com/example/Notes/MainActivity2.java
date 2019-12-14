package com.example.Notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesListAdapter adapter1;
    private SwipeController swiper;

    List<Note> noteArrayList = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private int counterKey = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        Toolbar myToolBar = findViewById(R.id.my_tool_bar);
        setSupportActionBar(myToolBar);


        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swiper = new SwipeController();

        ItemTouchHelper touchHelper = new ItemTouchHelper(swiper);

        touchHelper.attachToRecyclerView(recyclerView);

        adapter1 = new NotesListAdapter(preferenceReader(counterStorageReader(counterKey)));

        recyclerView.setAdapter(adapter1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivityForResult(new Intent(MainActivity2.this, GetNotesActivity.class), 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);

        return true;
    }

    private void notificationHandler(){
        View contextView = findViewById(R.id.relativeLayout);

        Snackbar.make(contextView, "Note saved", Snackbar.LENGTH_SHORT).show();
    }

    private int counterStorageReader(int counterKey){
        String counterPreferenceFileName = "notes_counter_storage";
        sharedPreferences = getSharedPreferences(counterPreferenceFileName, Context.MODE_PRIVATE);

        return sharedPreferences.getInt(Integer.toString(counterKey), 1);//(getString(counterKey), Integer.parseInt(""));
    }

    private List<Note> preferenceReader(int count) {
        String preferenceFileName = "notes_Storage";
        sharedPreferences = getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);

        Gson gson = new Gson();

        for(int i = 0; i <= sharedPreferences.getAll().size(); i++) {
            if (sharedPreferences.contains(Integer.toString(i))) {
                String json = sharedPreferences.getString(Integer.toString(i), "");

                Note note = gson.fromJson(json, Note.class);
                noteArrayList.add(note);
            }
        }

        if (noteArrayList == null) {
            noteArrayList = new ArrayList<>();
        }

        return noteArrayList;
    }
}