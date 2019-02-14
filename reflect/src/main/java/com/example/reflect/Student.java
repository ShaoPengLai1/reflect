package com.example.reflect;

import android.util.Log;

public class Student {
    private static final String TAG = "~~~~Student~~~~";
    private String studentName;
    private int studentAge;

    private Student(String studentName) {
        this.studentName = studentName;
    }


    private String show(String message){
        Log.i(TAG, "show: " + studentName + "," + studentAge + ","+ message);
        return "shaopenglai";
    }
}
