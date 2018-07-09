package com.example.koushik.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class sensors extends AppCompatActivity {
    static String message;
    static String[] sensorvalues=new String[4];
    TextView temp,gass,ir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        temp=(TextView)findViewById(R.id.temperature);
        gass=(TextView)findViewById(R.id.gas);
        ir=(TextView)findViewById(R.id.IR);
        temp.setText(sensorvalues[0]);
        gass.setText(sensorvalues[1]);
        ir.setText(sensorvalues[2]);


    }
}
