package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditNotesActivity extends AppCompatActivity {
    private EditText medittileofnote, meditcontentofnote;
    FloatingActionButton msaveeditnote;
    Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        meditcontentofnote = findViewById(R.id.editcontentofnote);
        medittileofnote= findViewById(R.id.edittileofnote);
        msaveeditnote = findViewById(R.id.saveeditnote);
        Toolbar toolbar = findViewById(R.id.toolbarofeditnote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        msaveeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditNotesActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });

        /*String noteTitle = data.getStringExtra("title");
        String notecontent = data.getStringExtra("content");
        medittileofnote.setText(noteTitle);
        meditcontentofnote.setText(notecontent);*/


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}