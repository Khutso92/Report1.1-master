package com.example.mlab_codetribe.report11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    //Intent to the registering activity.
    public void GoToRegister(View view){

        Intent intent = new Intent(this,AddTeacherStudentActivity.class);
        startActivity(intent);
    }


    //Intent to the registering activity.
    public void AddSubject(View view){

        Intent intent = new Intent(this,AddTeacherStudentActivity.class);
        startActivity(intent);
    }


}
