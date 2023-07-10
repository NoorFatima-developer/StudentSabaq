package com.example.studentsabaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    studentViewHolder adapter;
    RecyclerView.LayoutManager layoutManager;

    DatabaseHelper db;
    List<Student> studentList;

    Button ButtonAdd;
    List<Student> filteredStudentList;

    EditText editTextSearch;
    Button buttonSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager or GridLayoutManager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        editTextSearch = findViewById(R.id.editTextTextPersonName);
        buttonSearch = findViewById(R.id.search);

        // Create Student objects
        studentList = new ArrayList<>();
        studentList.add(new Student( 12, 3, 5, 10, 1, 20, 4, 4, "Ahmed"));
        studentList.add(new Student( 10, 4, 7, 12, 3, 25, 6, 6, "Fatima"));
        studentList.add(new Student( 14, 5, 3, 20, 1, 30, 2, 2, "Mohammed"));
        studentList.add(new Student( 12, 3, 5, 10, 1, 20, 4, 4, "Ahmed"));
        studentList.add(new Student( 10, 4, 7, 12, 3, 25, 6, 6, "Fatima"));
        studentList.add(new Student( 14, 5, 3, 20, 1, 30, 2, 2, "Mohammed"));
        studentList.add(new Student( 12, 3, 5, 10, 1, 20, 4, 4, "Ahmed"));
        studentList.add(new Student( 10, 4, 7, 12, 3, 25, 6, 6, "Fatima"));
        studentList.add(new Student( 14, 5, 3, 20, 1, 30, 2, 2, "Mohammed"));



        refreshGrid(studentList);

        ButtonAdd=findViewById(R.id.addstudent);

        ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an explicit intent to navigate to the target activity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editTextSearch.getText().toString().trim();
                List<Student> a= db.getAllStudents();
                filterStudentList(query,a);
            }
        });
    }



    private void filterStudentList(String query,List<Student> studentList) {
        filteredStudentList = new ArrayList<>();

        if (query.isEmpty()) {
            filteredStudentList=db.getAllStudents();
        } else {
            query = query.toLowerCase();
            for (Student student : studentList) {
                if (student.getName().toLowerCase().contains(query)) {
                    filteredStudentList.add(student);
                }
            }
            layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            adapter = new studentViewHolder(filteredStudentList,MainActivity.this);
            recyclerView.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }
    public void refreshGrid(List<Student> studentList) {
        db = new DatabaseHelper(this);
        db.deleteAllStudents();
        db.insertStudents(studentList);
        List<Student> studentList1 = db.getAllStudents();
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new studentViewHolder(studentList1,MainActivity.this);
        recyclerView.setAdapter(adapter);
        db.close();
    }


}
