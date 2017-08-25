package com.example.mlab_codetribe.report11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by mlab - codetribe on 8/18/2017.
 */

public class VivizDatabaseAdapter {

    VivzHelper helper;
    int cid;


    TextView txt_name ;
    public static String studentName = "";
    Context context;

    public VivizDatabaseAdapter(Context context) {
        helper = new VivzHelper(context);
    }


    //Inserting  person into DB
    public long insertData(String name, String password, String role) {
        //Ref. to the database
        SQLiteDatabase db = helper.getWritableDatabase();

        //Acts a map one can place a key and value
        ContentValues contentValues = new ContentValues();
        contentValues.put(VivzHelper.NAME, name);
        contentValues.put(VivzHelper.PASSWORD, password);
        contentValues.put(VivzHelper.ROLE, role);

        //Inserting data into the DB
        //@param table name,columnHack,fields to the DB
        long id = db.insert(VivzHelper.TABLE_NAME, null, contentValues);

        return id;
    }


    //Inserting  subjects into DB
    public long insertSubjects(String Subject_Code, String Subject_Name, String semester_Level) {
        long id = 0;
        //Ref. to the database
        SQLiteDatabase db = helper.getWritableDatabase();

        //Acts a map one can place a key and value
        ContentValues contentValues = new ContentValues();
        contentValues.put(VivzHelper.SUB_CODE, Subject_Code);
        contentValues.put(VivzHelper.SUB_NAME, Subject_Name);
        contentValues.put(VivzHelper.SUB_SEMESTER_LEVEL, semester_Level);


        return id;
    }


    //Inserting  MARKS into DB
    public long insertMarks(String test_1_mark, String test_2_mark, String assignment, String exam_mark, String sub_mark_id) {

        //Ref. to the database
        SQLiteDatabase db = helper.getWritableDatabase();

        //Acts a map one can place a key and value
        ContentValues contentValues = new ContentValues();
        contentValues.put(VivzHelper.MARKS_TEST_1, test_1_mark);
        contentValues.put(VivzHelper.MARKS_TEST_2, test_2_mark);
        contentValues.put(VivzHelper.MARKS_ASSIGNMENT, assignment);
        contentValues.put(VivzHelper.MARKS_EXAM, exam_mark);
        contentValues.put(VivzHelper.SUB_MARK_ID, sub_mark_id);

        //Inserting data into the DB
        //@param table name,columnHack,fields to the DB
        long id = db.insert(VivzHelper.TABLE_MARKS, null, contentValues);

        return id;
    }

    //Getting data from DB for person info.
    public String getInforSubjectMark() {

        SQLiteDatabase db = helper.getWritableDatabase();
        //SQL SELECT id,name,password from  FROM VIVZTABLE
        //@param table name,columns,selection,selectionArgs,groupBy,having,orderBy

        String[] columns = {VivzHelper.MARKS_ID,
                VivzHelper.MARKS_TEST_1,
                VivzHelper.MARKS_TEST_2,
                VivzHelper.MARKS_ASSIGNMENT,
                VivzHelper.MARKS_EXAM,
                VivzHelper.SUB_MARK_ID};


        Cursor cursor = db.query(VivzHelper.TABLE_MARKS, columns, null, null, null, null, null);


        // To appends all the data
        StringBuffer buffer = new StringBuffer();

        //loops throught the data in the DB
        while (cursor.moveToNext()) {

            int cid = cursor.getInt(0);
            String sub_ct1 = cursor.getString(1);
            String sub_ct2 = cursor.getString(2);
            String sub_assignment = cursor.getString(3);
            String sub_exam = cursor.getString(4);
            String sub_selected = cursor.getString(5);

            buffer.append("\nid: " + cid +
                    "\n" + "class test 1:" + sub_ct1 +
                    "\n class test 2 :" + sub_ct2 +
                    "\nassignment :" + sub_assignment +
                    "\nexam :" + sub_exam +
                    "\nSubject selected: " + sub_selected + "\n\n");
        }
        return buffer.toString();
    }


    //Getting data from DB for person info.
    public String getInforSubjects() {

        SQLiteDatabase db = helper.getWritableDatabase();
        //SQL SELECT id,name,password from  FROM VIVZTABLE
        //@param table name,columns,selection,selectionArgs,groupBy,having,orderBy


        String[] columns = {VivzHelper.SUB_ID, VivzHelper.SUB_CODE, VivzHelper.SUB_NAME, VivzHelper.SUB_SEMESTER_LEVEL};
        Cursor cursor = db.query(VivzHelper.TABLE_SUBJECT, columns, null, null, null, null, null);

        // To appends all the data
        StringBuffer buffer = new StringBuffer();

        //loops throught the data in the DB
        while (cursor.moveToNext()) {

            int cid = cursor.getInt(0);
            String sub_code = cursor.getString(1);
            String sub_name = cursor.getString(2);
            String sub_semester_level = cursor.getString(3);
            buffer.append("id: " + cid + "\n" + "subject code:" + sub_code + "\nsubject name :" + sub_name + "\nsemester level :" + sub_semester_level + "\n\n");
        }
        return buffer.toString();
    }


    //Generating a report for a student

    public String Report() {

        SQLiteDatabase db = helper.getWritableDatabase();

        //Table marks
        String[] tableMarksColumns = {
                VivzHelper.MARKS_ID,
                VivzHelper.MARKS_TEST_1,
                VivzHelper.MARKS_TEST_2,
                VivzHelper.MARKS_ASSIGNMENT,
                VivzHelper.MARKS_EXAM,
                VivzHelper.SUB_MARK_ID};


        //Table Person
        String tablePersonColums[] = {VivzHelper.UID,
                VivzHelper.NAME,
                VivzHelper.ROLE};

        //@param
        // table name,columns,selection,selectionArgs,groupBy,having,orderBy
        Cursor cursor = db.query(VivzHelper.TABLE_MARKS, tableMarksColumns, null, null, null, null, null);
        Cursor cursorPerson = db.query(VivzHelper.TABLE_NAME, tablePersonColums, null, null, null, null, null);



        // To store all the fields
        StringBuffer buffer = new StringBuffer();

        //Person
        while (cursorPerson.moveToNext()) {

            int p_id = cursorPerson.getInt(0);
            String name = cursorPerson.getString(1);
            String Role = cursorPerson.getString(2);


            if (Role.equals("student") && p_id==1) {
                buffer.append("\n\nname: " + name + "\nperson ID :" + p_id + "\nRole: " + Role + "\n\n\n");
                studentName =name;
            }
        }

        //marks
        while (cursor.moveToNext()) {

            cid = cursor.getInt(0);
            int class_test1 = cursor.getInt(1);
            int class_test2 = cursor.getInt(2);
            int assignment = cursor.getInt(3);
            int exam = cursor.getInt(4);


            String subject_selected = cursor.getString(5);

            // calc. average
            int avg = (int) ((class_test1 *0.15)+ (class_test2*0.15) + (assignment*0.20) +(exam*.50 ) );

            if(cid < 5) {
                buffer.append("class test 1:" +
                        class_test1 + "\nclass test 2:" +
                        class_test2 + "\nassignment: " +
                        assignment + "\nexam: "
                        + exam + "\naverage: " + avg +
                        "\ncomment: "+ comment(avg) +
                        "\nsubject name: " + subject_selected + "\n\n");

            }
        }

        return buffer.toString();

    }

    //determining the pass / fail and the comment
    public  String comment(int avg){
        String comment ;

        if (avg > 50) {

            comment = "pass ";
        }
        else {

            comment = "fail, try again next semester";
        }
        return comment;
    }

    //Getting data from DB for person info.
    public String getAllData() {

        SQLiteDatabase db = helper.getWritableDatabase();
        //SQL SELECT id,name,password from  FROM VIVZTABLE
        //@param table name,columns,selection,selectionArgs,groupBy,having,orderBy

        String[] columns = {VivzHelper.UID, VivzHelper.NAME, VivzHelper.PASSWORD, VivzHelper.ROLE};
        Cursor cursor = db.query(VivzHelper.TABLE_NAME, columns, null, null, null, null, null);

        // To appends all the data
        StringBuffer buffer = new StringBuffer();

        //loops throught the data in the DB
        while (cursor.moveToNext()) {

            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);
            String role = cursor.getString(3);
            buffer.append("id:" + cid + "\n" + "name: " + name + "\npassword: " + password + "\nrole: " + role + "\n\n");
        }
        return buffer.toString();
    }

    //Getting information for a specific user
    //@param user name

    //will be retrieved using a EditText
    public String getData(String name) {

        //SQL - SELECT name,password FROM vivztable WHERE name ="lesego";
        SQLiteDatabase db = helper.getWritableDatabase();

        //fields
        String[] columns = {VivzHelper.NAME, VivzHelper.PASSWORD};

        //@param table name,columns,selection,selectionArgs,groupBy,having,orderBy
        Cursor cursor = db.query(VivzHelper.TABLE_NAME, columns, VivzHelper.NAME + "= '" + name + "'", null, null, null, null);

        // To appends all the data
        StringBuffer buffer = new StringBuffer();

        //loops throught the data in the DB
        while (cursor.moveToNext()) {

            //getting index of each column
            int index1 = cursor.getColumnIndex(VivzHelper.NAME);
            int index2 = cursor.getColumnIndex(VivzHelper.PASSWORD);

            String personName = cursor.getString(index1);
            String password = cursor.getString(index2);

            buffer.append(personName + " " + password + "\n");
        }

        return buffer.toString();
    }

 /*     "(" + MARKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
    MARKS_TEST_1 + "  INTEGER ," +
    MARKS_TEST_2 + " INTEGER, " +
    MARKS_ASSIGNMENT + " INTEGER ," +
    MARKS_EXAM + " INTEGER ," +
    SUB_MARK_ID + " INTEGER );";*/

    //update update
    public int updateMark(String OldName, int newExamMark) {
        //UPDATE VIVZTABLE SET Name = "khutso" WHERE  name =?           kwaja"

        //DB ref.
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(VivzHelper.MARKS_EXAM, newExamMark);

        String[] whereArgs = {OldName};

        //tablename,newName,compareName in DB,condition
        int count = db.update(VivzHelper.TABLE_MARKS, contentValues, VivzHelper.SUB_MARK_ID + "=?", whereArgs);
        return count;

    }

    //delete row
    public int deleteRow() {

        //DELETE * FROM vivztable WHERE Name = "carlos"

        //must be entered via EditText
        String[] whereArgs = {"carlos"};

        //DB ref.
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete(VivzHelper.TABLE_NAME, VivzHelper.NAME + "=?", whereArgs);

        return count;

    }

    static class VivzHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "vivzdatabase";
        private static final String TABLE_NAME = "VIVZTABLE";
        private static final int DATABASE_VERSION = 11;

        private Context context;

        //DB fields for person/vivztable table
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String ROLE = "role";

        //DB fields for subjects
        private static final String TABLE_SUBJECT = "SUBJECT";
        private static final String SUB_ID = "_id";
        private static final String SUB_CODE = "Subject_code";
        private static final String SUB_NAME = "Name";
        private static final String SUB_SEMESTER_LEVEL = "semester_level";


        //DB fields for marks
        private static final String TABLE_MARKS = "MARKS";
        private static final String MARKS_ID = "_id";
        private static final String MARKS_TEST_1 = "Test_1";
        private static final String MARKS_TEST_2 = "Test_2";
        private static final String MARKS_ASSIGNMENT = "Assignment";
        private static final String MARKS_EXAM = "Exam";
        private static final String SUB_MARK_ID = "sub_mark_id";


        //Creating table for person
        //CREATE TABLE VIVZTABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT ,Name VARCHAR(255))
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "" + NAME + " VARCHAR(255) ," +
                PASSWORD + " VARCHAR(255), " +
                ROLE + " VARCHAR(255));";

        //Creating table for subject
        private static final String CREATE_TABLE_SUBJECT = "CREATE TABLE " + TABLE_SUBJECT +
                "(" + SUB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +    //put a system constraint
                " " + SUB_CODE + " VARCHAR(255) ," +
                "" + SUB_NAME + " VARCHAR(255), " +
                SUB_SEMESTER_LEVEL + " VARCHAR(255));";

        //Creating table for marks
        private static final String CREATE_TABLE_MARKS = "CREATE TABLE " + TABLE_MARKS +
                "(" + MARKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                MARKS_TEST_1 + "  INTEGER ," +
                MARKS_TEST_2 + " INTEGER, " +
                MARKS_ASSIGNMENT + " INTEGER ," +
                MARKS_EXAM + " INTEGER ," +
                SUB_MARK_ID + " INTEGER );";

        //Deleting the tables
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private static final String DROP_TABLE_SUBJECT = "DROP TABLE IF EXISTS " + TABLE_SUBJECT;
        private static final String DROP_TABLE_MARKS = "DROP TABLE IF EXISTS " + TABLE_MARKS;

        public VivzHelper(Context context) {

            //Context of the class,database name,custom cursor ,version number
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        //called when the DB is about to be created
        @Override
        public void onCreate(SQLiteDatabase db) {

            try {

                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_TABLE_SUBJECT);
                db.execSQL(CREATE_TABLE_MARKS);

            } catch (SQLException e) {
                Message.message(context, "" + e);
                Message.AlertMessage(context, e + "");

            }
        }


        //Called when modifying the table
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

            try {

                db.execSQL(DROP_TABLE);
                db.execSQL(DROP_TABLE_SUBJECT);
                db.execSQL(DROP_TABLE_MARKS);

                onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);
                Message.AlertMessage(context, e + "");
            }

        }
    }

}
