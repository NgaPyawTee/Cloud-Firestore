package com.homework.firestoreui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddNoteActivity extends AppCompatActivity {
    private EditText title, description;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");

        title = findViewById(R.id.edt_title);
        description = findViewById(R.id.edt_description);
        numberPicker = findViewById(R.id.nb_picker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_items,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                SaveNote();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SaveNote() {
        String strTitle = title.getText().toString();
        String strDescription = description.getText().toString();
        int intPriority = numberPicker.getValue();

        if (strTitle.trim().isEmpty() || strDescription.trim().isEmpty()){
            Toast.makeText(this, "Please insert both title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("Notebook");
        collectionRef.add(new Note(strTitle,strDescription,intPriority));
        Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
        finish();
    }
}