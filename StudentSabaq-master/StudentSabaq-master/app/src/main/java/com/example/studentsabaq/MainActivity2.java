package com.example.studentsabaq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextClass;

    private Button buttonSubmit;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextClass = findViewById(R.id.editTextClass);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        db = new DatabaseHelper(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addsudent();

            }
        });




    }
    public void addsudent(){
        String name = editTextName.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());
        int className = Integer.parseInt(editTextAge.getText().toString());



        Student obj = new Student(age , className,1,1,0,10,0,0,name);
        db.insertStudent(obj);

        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();




        db.close();

    }
}