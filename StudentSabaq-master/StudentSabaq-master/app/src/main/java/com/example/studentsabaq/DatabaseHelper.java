package com.example.studentsabaq;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENT = "students";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CLASS = "class";
    private static final String COLUMN_CURRENT_PARA = "current_para";
    private static final String COLUMN_SABAQ_START = "sabaq_start";
    private static final String COLUMN_SABAQ_END = "sabaq_end";
    private static final String COLUMN_SABQI = "sabqi";
    private static final String COLUMN_MANZIL_RANGE = "manzil_range";
    private static final String COLUMN_CURRENT_MANZIL_PARA = "current_manzil_para";
    private static final String COLUMN_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the student table
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_CLASS + " INTEGER, " +
                COLUMN_CURRENT_PARA + " INTEGER, " +
                COLUMN_SABAQ_START + " INTEGER, " +
                COLUMN_SABAQ_END + " INTEGER, " +
                COLUMN_SABQI + " INTEGER, " +
                COLUMN_MANZIL_RANGE + " INTEGER, " +
                COLUMN_CURRENT_MANZIL_PARA + " INTEGER, " +
                COLUMN_NAME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop and recreate the student table on database upgrade
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_STUDENT;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
    public void updateSabaqRange(int id, int sabaqStart, int sabaqEnd) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SABAQ_START, sabaqStart);
        values.put(COLUMN_SABAQ_END, sabaqEnd);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.update(TABLE_STUDENT, values, whereClause, whereArgs);
        db.close();
    }
    public void updateManzilRangeAndSabqi(int id, int manzilRange, int sabqi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MANZIL_RANGE, manzilRange);
        values.put(COLUMN_SABQI, sabqi);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.update(TABLE_STUDENT, values, whereClause, whereArgs);
        db.close();
    }

    public void updateCurrentManzilPara(int id, int currentManzilPara) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENT_MANZIL_PARA, currentManzilPara+1);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.update(TABLE_STUDENT, values, whereClause, whereArgs);
        db.close();
    }
    public void updatePara(int id, int currentPara) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENT_PARA, currentPara+1);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.update(TABLE_STUDENT, values, whereClause, whereArgs);
        db.close();
    }



    // Insert a student record with image and name
    public void insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Retrieve the last inserted ID
        Cursor cursor = db.rawQuery("SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_STUDENT, null);


        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENT_PARA, student.getCurrent_para());
        values.put(COLUMN_SABAQ_START, student.getSabaq_start());
        values.put(COLUMN_SABAQ_END, student.getSabaq_end());
        values.put(COLUMN_SABQI, student.getSabqi());
        values.put(COLUMN_MANZIL_RANGE, student.getManzil_range());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE,student.getAge());
        values.put(COLUMN_CLASS,student.getCls());
        values.put(COLUMN_CURRENT_MANZIL_PARA,student.getCurrent_manzil_para());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public void deleteAllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, null, null);
        db.close();
    }

    public void insertStudents(List<Student> students) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            for (Student student : students) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_CURRENT_PARA, student.getCurrent_para());
                values.put(COLUMN_SABAQ_START, student.getSabaq_start());
                values.put(COLUMN_SABAQ_END, student.getSabaq_end());
                values.put(COLUMN_SABQI, student.getSabqi());
                values.put(COLUMN_MANZIL_RANGE, student.getManzil_range());
                values.put(COLUMN_NAME, student.getName());
                values.put(COLUMN_AGE,student.getAge());
                values.put(COLUMN_CLASS,student.getCls());
                values.put(COLUMN_CURRENT_MANZIL_PARA,student.getCurrent_manzil_para());
                db.insert(TABLE_STUDENT, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public Student getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[]{COLUMN_ID, COLUMN_CURRENT_PARA, COLUMN_SABAQ_START, COLUMN_SABAQ_END,
                        COLUMN_SABQI, COLUMN_CURRENT_MANZIL_PARA, COLUMN_MANZIL_RANGE, COLUMN_NAME, COLUMN_AGE, COLUMN_CLASS},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int current_paraIndex = cursor.getColumnIndex(COLUMN_CURRENT_PARA);
            int sabaqStartIndex = cursor.getColumnIndex(COLUMN_SABAQ_START);
            int sabaqEndIndex = cursor.getColumnIndex(COLUMN_SABAQ_END);
            int sabqiIndex = cursor.getColumnIndex(COLUMN_SABQI);
            int current_manzil_paraIndex = cursor.getColumnIndex(COLUMN_CURRENT_MANZIL_PARA);
            int manzilRangeIndex = cursor.getColumnIndex(COLUMN_MANZIL_RANGE);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int ageIndex = cursor.getColumnIndex(COLUMN_AGE);
            int classIndex = cursor.getColumnIndex(COLUMN_CLASS);

            // Check if the column indexes are valid
            if (idIndex >= 0 && sabaqStartIndex >= 0 && sabaqEndIndex >= 0 && sabqiIndex >= 0 && manzilRangeIndex >= 0 && nameIndex >= 0) {
                cursor.moveToFirst();
                int curr_para = cursor.getInt(current_paraIndex);
                int sabaqStart = cursor.getInt(sabaqStartIndex);
                int sabaqEnd = cursor.getInt(sabaqEndIndex);
                int sabqi = cursor.getInt(sabqiIndex);
                int current_manzil_para = cursor.getInt(current_manzil_paraIndex);
                int manzilRange = cursor.getInt(manzilRangeIndex);
                String name = cursor.getString(nameIndex);
                int age = cursor.getInt(ageIndex);
                int cls = cursor.getInt(classIndex);

                cursor.close();
                return new Student(id, age, cls, curr_para, sabaqStart, current_manzil_para, sabaqEnd, sabqi, manzilRange, name);
            } else {
                cursor.close();
                return null;
            }
        }

        return null;
    }



    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT " + COLUMN_ID + ", " + COLUMN_CURRENT_PARA + ", " + COLUMN_SABAQ_START +
                ", " + COLUMN_SABAQ_END + ", " + COLUMN_SABQI + ", " + COLUMN_CURRENT_MANZIL_PARA +
                ", " + COLUMN_MANZIL_RANGE + ", " + COLUMN_NAME + ", " + COLUMN_AGE + ", " + COLUMN_CLASS +
                " FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") int current_para = cursor.getInt(cursor.getColumnIndex(COLUMN_CURRENT_PARA));
                @SuppressLint("Range") int sabaqStart = cursor.getInt(cursor.getColumnIndex(COLUMN_SABAQ_START));
                @SuppressLint("Range") int sabaqEnd = cursor.getInt(cursor.getColumnIndex(COLUMN_SABAQ_END));
                @SuppressLint("Range") int sabqi = cursor.getInt(cursor.getColumnIndex(COLUMN_SABQI));
                @SuppressLint("Range") int current_manzil_para = cursor.getInt(cursor.getColumnIndex(COLUMN_CURRENT_MANZIL_PARA));
                @SuppressLint("Range") int manzilRange = cursor.getInt(cursor.getColumnIndex(COLUMN_MANZIL_RANGE));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                @SuppressLint("Range") int cls = cursor.getInt(cursor.getColumnIndex(COLUMN_CLASS));

                Student student = new Student(id, age, cls, current_para, sabaqStart, current_manzil_para, sabaqEnd, sabqi, manzilRange, name);
                studentList.add(student);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return studentList;
    }

}
