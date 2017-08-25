package com.example.mlab_codetribe.report11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddSubjectMark extends AppCompatActivity {


    RadioButton rad_subject1, rad_subject2, rad_subject3, rad_subject4;
    EditText edt_ct1, edt_ct2, edt_assignment, edt_exam;

    VivizDatabaseAdapter vivizHelper;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject_mark);

        //Ref. the radio buttons
        rad_subject1 = (RadioButton) findViewById(R.id.rad_subject1);
        rad_subject2 = (RadioButton) findViewById(R.id.rad_subject2);
        rad_subject3 = (RadioButton) findViewById(R.id.rad_subject3);
        rad_subject4 = (RadioButton) findViewById(R.id.rad_subject4);


        //Ref. the EditTexts
        edt_ct1 = (EditText) findViewById(R.id.edt_ct1);
        edt_ct2 = (EditText) findViewById(R.id.edt_ct2);
        edt_assignment = (EditText) findViewById(R.id.edt_assignment);
        edt_exam = (EditText) findViewById(R.id.edt_exam);

        vivizHelper = new VivizDatabaseAdapter(this);

    }


    //inserting the marks for a specific subjects
    public void AddSubjectMarks(View view) {

        String class_test_1 = edt_ct1.getText().toString().trim();
        String class_test_2 = edt_ct2.getText().toString().trim();
        String assignment = edt_assignment.getText().toString().trim();
        String exam = edt_exam.getText().toString().trim();

        //validates that a subject is selected
        if (rad_subject1.isChecked() || rad_subject2.isChecked() || rad_subject3.isChecked() || rad_subject4.isChecked()) {

            String subjectChecked;

            if (rad_subject1.isChecked()) {

                subjectChecked = "C++";

            } else if (rad_subject2.isChecked()) {


                subjectChecked = "C#";

            } else if (rad_subject3.isChecked()) {
                subjectChecked = "Java";

            } else {

                subjectChecked = "Ruby";
            }


            if (class_test_1.length() > 0 && class_test_2.length() > 0 && assignment.length() > 0 && assignment.length() > 0 && exam.length() > 0) {

                long id = vivizHelper.insertMarks(class_test_1, class_test_2, assignment, exam,subjectChecked);

                //validates if the row was inserted
                if (id > 0) {
                    Message.message(this, "successfully inserted");

                    edt_ct1.setText("");
                    edt_ct2.setText("");
                    edt_assignment.setText("");
                    edt_exam.setText("");

                } else {
                    Message.message(this, "Row not inserted");
                }

            } else {

                Message.message(this, "All fields required");
            }


        } else {
            Message.message(this, "A subject must selected ");
        }


    }


    //getting the data for person
    public void getInforSubjectMark(View view) {
        String data = vivizHelper.getInforSubjectMark();

        Message.AlertMessage(this,data);

    }
}
