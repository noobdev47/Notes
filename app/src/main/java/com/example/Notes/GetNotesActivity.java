package com.example.Notes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

public class GetNotesActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String preferenceFileName = "notes_Storage";
    private String counterPreferenceFileName = "notes_counter_storage";
    private int counterKey = 1;
    private int counter = 1;
    private int size = 0;
    private int i = 1;
    private List<Note> noteArrayList = new ArrayList<>();
    ArrayList<Object> noteObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_notes);
    }

    public void getData(View view) {
        EditText title = findViewById(R.id.titleEditText);
        EditText note = findViewById(R.id.noteEditText);

        if(authenticator(title)) {}
        else
            saveNote(title, note);
    }

    private void saveNote(@NotNull EditText title, EditText note) {
        String t = title.getText().toString();
        String n = note.getText().toString();
        String d = dateGetter();

        Note newNote = new Note(t, n, d);

        sharedPreferences = getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(newNote);

        size = sharedPreferenceSize();

        editor.putString(Integer.toString(size), json);
        editor.commit();

        counterStorage(i);

        notificationHandler();

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

        finish();
    }

    private void counterStorage(int i) {
        sharedPreferences = getSharedPreferences(counterPreferenceFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        i++;

        editor.putInt(Integer.toString(i), counter);
        editor.commit();
    }

    private int counterRetriever(int counterKey){
        sharedPreferences = getSharedPreferences(counterPreferenceFileName, Context.MODE_PRIVATE);

        counter = sharedPreferences.getInt(Integer.toString(counterKey), 1);

        return counter;
    }

    private int sharedPreferenceSize() {
        sharedPreferences = getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        return sharedPreferences.getAll().size();
    }

    private void notificationHandler(){
        View contextView = findViewById(R.id.constraintLayout);

        Snackbar.make(contextView, "Note saved", Snackbar.LENGTH_SHORT).show();
    }

    private boolean authenticator(@NotNull EditText title){
        String t = title.getText().toString();

        if(TextUtils.isEmpty(t)) {
            title.setError("Cannot leave Title empty.");
            return true;
        }
        else
            return false;
    }

    private String dateGetter(){
        Formatter fmt;
        Calendar cal = Calendar.getInstance();
        fmt = new Formatter();

        return (fmt.format("%tb %tm", cal, cal)).toString();

    }
}