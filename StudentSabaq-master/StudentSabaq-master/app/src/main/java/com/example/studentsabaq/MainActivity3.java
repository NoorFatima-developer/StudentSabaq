package com.example.studentsabaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    TextView studentname;
    TextView parano;

    TextView sabaqtextview;

    Button sabaqdone;
    Button sabaqrepeat;
    Button assignsabaq;


    TextView sabqitextview;


    TextView manziltextview;
    Button manzildone;
    Button manzilrepeat;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        studentname=findViewById(R.id.StudentName);
        parano=findViewById(R.id.parano);

        sabaqtextview=findViewById(R.id.SabaqRange);
        sabaqdone=findViewById(R.id.SabaqDoneButton);
        sabaqrepeat=findViewById(R.id.SabaqRepeatButton);
        assignsabaq=findViewById(R.id.AssignSabaq);


        sabqitextview=findViewById(R.id.SabqiParaNo);

        manziltextview=findViewById(R.id.ManzilParaNo);
        manzildone=findViewById(R.id.ManzilDone);
        manzilrepeat=findViewById(R.id.ManzilRepeat);

        assignsabaq.setEnabled(false);
        assignsabaq.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButtonColor));

        Intent a = getIntent();
        int id = a.getIntExtra("studentId",0);

        db = new DatabaseHelper(this);
        Student student=db.getStudentById(id);

        studentname.setText(student.getName());
        sabaqtextview.setText(String.valueOf(student.getSabaq_start() +" -  " + student.getSabaq_end()));

        sabqitextview.setText(String.valueOf(student.getSabqi()));

        parano.setText(String.valueOf(student.getCurrent_para()));

        manziltextview.setText(String.valueOf(student.getCurrent_manzil_para()));
        assignsabaq.setEnabled(false);
        assignsabaq.setBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.disabledButtonColor));

        manzilrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manzildone.setEnabled(false);
                manzildone.setBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.disabledButtonColor));
            }
        });

        sabaqdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sabaqrepeat.setEnabled(false);
                sabaqrepeat.setBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.disabledButtonColor));
                assignsabaq.setEnabled(true);
                assignsabaq.setBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.purple_500));
            }
        });
        sabaqrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sabaqdone.setEnabled(false);
                sabaqdone.setBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.disabledButtonColor));
            }
        });
        assignsabaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                intent.putExtra("ID",student.getId());
                startActivity(intent);


            }
        });

        manzildone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manzilrepeat.setEnabled(false);
                manzilrepeat.setBackgroundColor(ContextCompat.getColor(MainActivity3.this, R.color.disabledButtonColor));
                if(student.getCurrent_manzil_para()< student.getManzil_range()){
                    db.updateCurrentManzilPara(id,student.getCurrent_manzil_para());
                    manzildone.setEnabled(false);
                    manzildone.setBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.disabledButtonColor));
                    manziltextview.setText(String.valueOf(student.getCurrent_manzil_para()));
                }
                else{
                    db.updateCurrentManzilPara(id,0);
                    manzildone.setEnabled(false);
                    manzildone.setBackgroundColor(ContextCompat.getColor(MainActivity3.this,R.color.disabledButtonColor));
                    manziltextview.setText(String.valueOf(student.getCurrent_manzil_para()));
                }
            }

        });









    }
}