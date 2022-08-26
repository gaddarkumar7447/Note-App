package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteDetailsActivity extends AppCompatActivity {
    private TextView mtitleofNoteDetails, mcontentofNoteDetails;
    FloatingActionButton mgotoeditNote;
    ImageView mbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details2);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        mtitleofNoteDetails = findViewById(R.id.titleofNoteDetail);
        mcontentofNoteDetails = findViewById(R.id.contentofNoteDetails);
        mgotoeditNote = findViewById(R.id.gotoeditNote);
        mbackButton = findViewById(R.id.backBotton);

        mbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        Intent data = getIntent();
        mgotoeditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditNotesActivity.class);
                intent.putExtra("title",data.getStringExtra("title"));
                intent.putExtra("content",data.getStringExtra("content"));
                intent.putExtra("noteId",data.getStringExtra("noteId"));
                v.getContext().startActivity(intent);
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
