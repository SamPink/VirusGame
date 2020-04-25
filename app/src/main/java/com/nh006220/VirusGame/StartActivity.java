package com.nh006220.VirusGame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    private ListView listView;
    private Button buttonLevels, buttonFree, buttonSettings;

    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        // Find the ListView
        listView = findViewById(R.id.ListView_high_scores);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("highScore");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayList = new ArrayList<>();
                String value = dataSnapshot.getValue(String.class);
                arrayList.add(value);
                adapter = new ArrayAdapter<>(StartActivity.this, android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonLevels = findViewById(R.id.button_play_levels);
        buttonLevels.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Bundle b = new Bundle();
            b.putInt("key", 1); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
        });

        buttonFree = findViewById(R.id.button_free_play);
        buttonFree.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Bundle b = new Bundle();
            b.putInt("key", 2); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
        });
    }
}
