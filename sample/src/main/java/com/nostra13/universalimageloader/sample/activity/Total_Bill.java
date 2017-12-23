package com.nostra13.universalimageloader.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nostra13.universalimageloader.sample.R;

public class Total_Bill extends AppCompatActivity {

    public double TotalBill=0.0;
    private TextView bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total__bill);
        ChoiceNumber ch=new ChoiceNumber();
        TotalBill=ch.inTotalBill;
        bill=(TextView)findViewById(R.id.bill);
        bill.setText(Double.toString(TotalBill)+"Tk");
    }
}
