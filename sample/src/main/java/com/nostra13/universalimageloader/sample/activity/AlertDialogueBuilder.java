package com.nostra13.universalimageloader.sample.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by abdullah on 6/29/16.
 */
public class AlertDialogueBuilder {
    public static void Builder(Activity main, String message)
    {
        new AlertDialog.Builder(main)

                .setTitle("Something Went Wrong !!!")
                .setMessage(message)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private String m_Text = "";
    public void DialogueBox(Activity m,String message)
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(m);
        builder.setTitle("Message !!!");
        builder.setMessage(message);


        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // continue with delete
        }
        });
            builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
    }
}
