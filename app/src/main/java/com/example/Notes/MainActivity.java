package com.example.Notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> notesarr = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private String preferenceFileName = "notesPref";
    private String counterPreferenceFileName = "notesCntrPref";
    private int counterKey = 1;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.listviewactivity, preferenceReader(counterStorageReader(counterKey)));

            listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
        }

//        try {
//            int[] totalNotes = counterStorageReader();
//
////            String[] notes = {"Android","IPhone","WindowsMobile","Blackberry",
////                    "WebOS","Ubuntu","Windows7","Max OS X"};//preferenceReader(totalNotes);
////
////            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.listviewactivity, notes);
////
////            ListView listView = findViewById(R.id.listView);
////            listView.setAdapter(adapter);
//
//            //List<Note> objectList = (List)notes;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivityForResult(new Intent(MainActivity.this, GetNotesActivity.class), 1);
            }
        });
    }

    private int counterStorageReader(int counterKey) {
        sharedPreferences = getSharedPreferences(counterPreferenceFileName, Context.MODE_PRIVATE);

        return sharedPreferences.getInt(Integer.toString(counterKey), 1);
    }

    private List<String> preferenceReader(int count){
        sharedPreferences = getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);

        for(int i = 0; i <= count; i++)
            if (sharedPreferences.contains(Integer.toString(i)))
                notesarr.add(sharedPreferences.getString(Integer.toString(i), ""));

        return notesarr;
    }
}
