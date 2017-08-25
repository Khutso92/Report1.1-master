package com.example.mlab_codetribe.report11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {


    VivizDatabaseAdapter vivizHelper;

    TextView txt_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //initialize the object of this class
        vivizHelper = new VivizDatabaseAdapter(this);

        txt_name = (TextView) findViewById(R.id.txt_name);

        vivizHelper.Report();
        txt_name.setText( vivizHelper.Report() );
    }

    public void MoveToMain(View view){

          vivizHelper.Report();

    //    Message.AlertMessage(this,data);
    }
}
