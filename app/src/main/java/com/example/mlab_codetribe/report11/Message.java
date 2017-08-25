package com.example.mlab_codetribe.report11;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by mlab - codetribe on 8/18/2017.
 */

public class Message {

    public static void message(Context context , String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void AlertMessage( Context context,String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage( msg);
        builder.create();
        builder.show();
    }
}
