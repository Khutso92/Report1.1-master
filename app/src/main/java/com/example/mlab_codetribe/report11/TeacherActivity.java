package com.example.mlab_codetribe.report11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
    }


    //Intent to Add subject Mark Activity
    public void GotToAddSubjectMark(View view) {

        Intent intent = new Intent(this, AddSubjectMark.class);
        startActivity(intent);
    }

    //Intent to the Update subject mark Activity
    public void GotToUpdateSubjectMark(View view) {
        Intent intent = new Intent(this, UpdateSubjectMarkActivity.class);
        startActivity(intent);
    }
}
