package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class NoteDetails extends AppCompatActivity {

    private TextView mtitleofNoteDetails, mcontentofNoteDetails;
    FloatingActionButton mgotoeditNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtitleofNoteDetails = findViewById(R.id.titleofNoteDetails);
        mcontentofNoteDetails = findViewById(R.id.contentofNoteDetails);
        mgotoeditNote = findViewById(R.id.gotoeditNote);
        Toolbar toolbar = findViewById(R.id.toolbarOfNoteDetails);
        setSupportActionBar(toolbar);

        Intent data = getIntent();
        mgotoeditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditNotesActivity.class);
                intent.putExtra("title",data.getStringExtra("title"));
                intent.putExtra("content",data.getStringExtra("content"));
                intent.putExtra("noteId",data.getStringExtra("noteId"));
                view.getContext().startActivity(intent);
            }
        });

        mcontentofNoteDetails.setText(data.getStringExtra("content"));
        mtitleofNoteDetails.setText(data.getStringExtra("title"));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}