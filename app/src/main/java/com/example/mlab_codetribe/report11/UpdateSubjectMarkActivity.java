package com.example.mlab_codetribe.report11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class UpdateSubjectMarkActivity extends AppCompatActivity {


    VivizDatabaseAdapter vivizHelper;

    RadioButton sub1, sub2, sub3, sub4;

    EditText txt_exam_mark, txt_class_test1, txt_class_test2, txt_ass_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject_mark);

        vivizHelper = new VivizDatabaseAdapter(this);

        sub1 = (RadioButton) findViewById(R.id.sub1);
        sub2 = (RadioButton) findViewById(R.id.sub2);
        sub3 = (RadioButton) findViewById(R.id.sub3);
        sub4 = (RadioButton) findViewById(R.id.sub4);

        txt_class_test1 = (EditText) findViewById(R.id.txt_class_test1);
        txt_class_test2 = (EditText) findViewById(R.id.txt_class_test2);
        txt_ass_mark = (EditText) findViewById(R.id.txt_ass_mark);
        txt_exam_mark = (EditText) findViewById(R.id.txt_exam_mark);

        txt_class_test1.setVisibility(View.GONE);
        txt_class_test2.setVisibility(View.GONE);
        txt_ass_mark.setVisibility(View.GONE);

    }


    public void update(View view) {


        String examMark = txt_exam_mark.getText().toString().trim();


        if ( examMark.length()> 0) {

            if ((Integer.parseInt( examMark )>= 0 && Integer.parseInt( examMark) <= 100)) {

                String subject = "";
                if (sub1.isChecked() || sub2.isChecked() || sub3.isChecked() || sub4.isChecked()) {


                    if (sub1.isChecked()) {

                        subject = "C++";

                    } else if (sub2.isChecked()) {

                        subject = "C#";

                    } else if (sub3.isChecked()) {

                        subject = "Java";

                    } else {

                        subject = "ruby";

                    }

                    vivizHelper.updateMark(subject, Integer.parseInt( examMark));

                    Message.message(this, "successfully updated exam mark");

                } else {
                    Message.message(this, "select subject you wanna update");

                }

            } else {
                Message.message(this, "invalid input");
            }


        } else {
            Message.message(this, "exam mark required");
        }

    }

}
