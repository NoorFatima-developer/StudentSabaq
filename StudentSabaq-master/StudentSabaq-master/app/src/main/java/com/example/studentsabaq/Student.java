package com.example.studentsabaq;

public class Student {
    public Student(int age, int cls, int current_para, int sabaq_start, int current_manzil_para, int sabaq_end, int sabqi, int manzil_range, String name) {
        this.age = age;
        this.cls = cls;
        this.current_para = current_para;
        this.sabaq_start = sabaq_start;
        this.current_manzil_para = current_manzil_para;
        this.sabaq_end = sabaq_end;
        this.sabqi = sabqi;
        this.manzil_range = manzil_range;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student(int id, int age, int cls, int current_para, int sabaq_start, int current_manzil_para, int sabaq_end, int sabqi, int manzil_range, String name) {
        this.id = id;
        this.age = age;
        this.cls = cls;
        this.current_para = current_para;
        this.sabaq_start = sabaq_start;
        this.current_manzil_para = current_manzil_para;
        this.sabaq_end = sabaq_end;
        this.sabqi = sabqi;
        this.manzil_range = manzil_range;
        this.name = name;
    }

    private int id;


    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCls() {
        return cls;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    private int cls;



    public int getCurrent_para() {
        return current_para;
    }

    public void setCurrent_para(int current_para) {
        this.current_para = current_para;
    }



    private int current_para;
    private int sabaq_start;

    public int getCurrent_manzil_para() {
        return current_manzil_para;
    }

    public void setCurrent_manzil_para(int current_manzil_para) {
        this.current_manzil_para = current_manzil_para;
    }


    private int current_manzil_para;



    private int sabaq_end;
    private int sabqi;
    private int manzil_range;

    private String name;

    public int getSabaq_start() {
        return sabaq_start;
    }

    public int getSabaq_end() {
        return sabaq_end;
    }

    public int getSabqi() {
        return sabqi;
    }

    public int getManzil_range() {
        return manzil_range;
    }







    public void setSabaq_start(int sabaq_start) {
        this.sabaq_start = sabaq_start;
    }

    public void setSabaq_end(int sabaq_end) {
        this.sabaq_end = sabaq_end;
    }

    public void setSabqi(int sabqi) {
        this.sabqi = sabqi;
    }

    public void setManzil_range(int manzil_range) {
        this.manzil_range = manzil_range;
    }

    public void setName(String name) {
        this.name = name;
    }









    public String getName() {
        return name;
    }
}


