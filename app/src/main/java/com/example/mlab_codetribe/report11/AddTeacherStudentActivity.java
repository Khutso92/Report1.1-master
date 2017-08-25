package com.example.mlab_codetribe.report11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddTeacherStudentActivity extends AppCompatActivity {

    //edit text for name and password
    EditText name, password;

    RadioButton rad_teacher, rad_student;

    VivizDatabaseAdapter vivizHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_student);

        name = (EditText) findViewById(R.id.edt_name);
        password = (EditText) findViewById(R.id.edt_password);

        rad_student = (RadioButton) findViewById(R.id.rad_student);
        rad_teacher = (RadioButton) findViewById(R.id.rad_teacher);

        //initialize the object of this class
        vivizHelper = new VivizDatabaseAdapter(this);

    }

    //adding the user (teacher and student)
    public void addUser(View view){
        String user = name.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (name.length() > 0 && password.length() > 0 && (rad_student.isChecked() || rad_teacher.isChecked())) {

            String role;
            //testing for the role
            if (rad_teacher.isChecked()){
                role = "teacher";
            } else {
                role = "student";
            }


            //calling the method for insert data into DB
            long id = vivizHelper.insertData(user, pass, role);

            //validates if the row was inserted
            if (id > 0) {
                Message.message(this, "successfully inserted");


                name.setText("");
                password.setText("");
            } else {
                Message.message(this, "Row not inserted");
            }

        } else {
            Message.message(this, "All fields required");
        }
    }


    //getting the data for person
    public void viewDetails(View view) {
        String data = vivizHelper.getAllData();

        Message.AlertMessage(this,data);

    }
}
