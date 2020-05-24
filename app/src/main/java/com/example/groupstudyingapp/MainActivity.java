package com.example.groupstudyingapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTile, editTextDescription;
    private Button saveButton;
    AppData appData;
    FireStoreHandler fireStoreHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        getAppData();
        setViews();

        setButtonsClickListeners();
    }



    interface OnItemCreated {
        void itemCreated(String title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setButtonsClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTile.getText().toString();
                String description = editTextDescription.getText().toString();
                Note note = new Note();
                note.setTitle(title);
                note.setDescription(description);

                fireStoreHandler.saveNote(note, getBaseContext());
            }
        });
    }

    private void setViews() {
        editTextTile = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        saveButton = findViewById(R.id.save_button);
    }

    private void getAppData() {
        appData = (AppData) getApplicationContext();
        fireStoreHandler = appData.fireStoreHandler;
    }


}