package com.nostra13.universalimageloader.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.nostra13.universalimageloader.sample.R;

public class About extends AppCompatActivity {

    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        link =(TextView)findViewById(R.id.link);
        link.setClickable(true);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://sites.google.com/site/abdullahalrifatcse'>Developed By : Abdullah Al Rifat </a>";
        link.setText(Html.fromHtml(text));
    }
}
