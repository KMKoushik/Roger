package com.example.koushik.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class devicepage extends AppCompatActivity {
    ImageButton btnevice1;
    ImageButton btnevice2;
    ImageButton btnevice3;
    ImageButton btnlock;
    Button btnSensor;
    Button alertbutton;
    TextView onoff1;
    TextView onoff2;
    TextView onoff3;
    TextView lockstatus;
    EditText ipaddress;
    Handler h = new Handler();
    int delay = 1000;

    boolean onoffstat1=FALSE,onoffstat2=FALSE,onoffstat3=FALSE,lockstatbool=FALSE;
    mqttSub mqttobj;
    static String ipstring;
    static String alertmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicepage);
        onoff1=(TextView) findViewById(R.id.status1);
        onoff2=(TextView) findViewById(R.id.status2);
        onoff3=(TextView) findViewById(R.id.status3);
        lockstatus=(TextView)findViewById(R.id.lockstat);

        ipaddress=(EditText)findViewById(R.id.getip);

        //ipstring=ipaddress.getText().toString();

       /* Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("EMERGENCY");
        builder1.setMessage("ennada panra");
        builder1.setCancelable(true);
        builder1.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        mp.stop();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();*/
        //displayAlert("hi");
        //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                //do something
                displayAlert1();
                h.postDelayed(this, delay);
            }
        }, delay);

        mqttobj=new mqttSub();
        switchStatus();
        btnevice1=(ImageButton)findViewById(R.id.button1) ;



        btnevice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               onoffstat1=!onoffstat1;
                switchStatus();
                ipstring=ipaddress.getText().toString();

                if(onoffstat1==TRUE)
                    mqttobj.mymqtt("control/send","{\"device_name\":\"dev1\",\"device_type\":\"MQTT\",\"status\":\"1\"}",ipstring);
                else
                    mqttobj.mymqtt("control/send","{\"device_name\":\"dev1\",\"device_type\":\"MQTT\",\"status\":\"0\"}",ipstring);


              System.out.println("gotcha");
            }
        });
        btnevice2=(ImageButton) findViewById(R.id.button2) ;

        btnevice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onoffstat2=!onoffstat2;
                switchStatus();
                ipstring=ipaddress.getText().toString();
                if(onoffstat2==TRUE)
                mqttobj.mymqtt("control/send","{\"device_name\":\"dev2\",\"device_type\":\"bluetooth\",\"status\":\"1\"}",ipstring);
                else
                    mqttobj.mymqtt("control/send","{\"device_name\":\"dev2\",\"device_type\":\"bluetooth\",\"status\":\"0\"}",ipstring);

            }
        });
        btnevice3=(ImageButton) findViewById(R.id.button3) ;

        btnevice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onoffstat3=!onoffstat3;
                switchStatus();
                ipstring=ipaddress.getText().toString();
                if(onoffstat3==TRUE)
                mqttobj.mymqtt("control/send","{\"device_name\":\"dev3\",\"device_type\":\"xbee\",\"status\":\"1\"}",ipstring);
                else
                    mqttobj.mymqtt("control/send","{\"device_name\":\"dev3\",\"device_type\":\"xbee\",\"status\":\"0\"}",ipstring);
            }
        });
        btnlock=(ImageButton)findViewById(R.id.lockbutton);
        btnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockstatbool=!lockstatbool;
                switchStatus();
                ipstring=ipaddress.getText().toString();
                if(lockstatbool==TRUE)
                    mqttobj.mymqtt("control/send","{\"device_name\":\"lock\",\"device_type\":\"lock\",\"status\":\"1\"}",ipstring);
                else
                    mqttobj.mymqtt("control/send","{\"device_name\":\"dev2\",\"device_type\":\"bluetooth\",\"status\":\"0\"}",ipstring);


            }
        });
        btnSensor=(Button)findViewById(R.id.sensorvalues);
        btnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newintent=new Intent(devicepage.this,sensors.class);
                startActivity(newintent);
                mqttSub1 mqttreq;
                mqttreq=new mqttSub1();
                ipstring=ipaddress.getText().toString();
                mqttreq.mymqtt(ipstring);
            }
        });

        alertbutton=(Button)findViewById(R.id.btnAlert);
        alertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlert1();mqttSub2 mqttreqalert;
                mqttreqalert=new mqttSub2();
                ipstring=ipaddress.getText().toString();
                mqttreqalert.mymqtt(ipstring);


            }
        });

    }
    public void switchStatus()
    {
        if(onoffstat1==FALSE)
            onoff1.setText("OFF");
        if(onoffstat1==TRUE)
            onoff1.setText("ON");
        if(onoffstat2==FALSE)
            onoff2.setText("OFF");
        if(onoffstat2==TRUE)
            onoff2.setText("ON");
        if(onoffstat3==FALSE)
            onoff3.setText("OFF");
        if(onoffstat3==TRUE)
            onoff3.setText("ON");
        if(lockstatbool==FALSE)
            lockstatus.setText("UNLOCKED");
        if(lockstatbool==TRUE)
            lockstatus.setText("LOCKED");


    }


   /* public void displayAlert(String alertmessage)
    {
        System.out.print("hello");

        if(alertmessage!=null)
        {
            System.out.print("hello");

            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
            mp.start();

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("emegency");
            builder1.setMessage(alertmessage);
            builder1.setCancelable(true);
            builder1.setNeutralButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            mp.stop();
                        }
                    });
             AlertDialog alert11 = builder1.create();
            alert11.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            alert11.show();
        }
    }*/
    public void displayAlert1()
    {
        System.out.print("hellopeople");
        mqttSub2 mqttreqalert;
        mqttreqalert=new mqttSub2();
        ipstring=ipaddress.getText().toString();
        mqttreqalert.mymqtt(ipstring);
        if(alertmessage!=null)
        {

            System.out.print("helloguys");

            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
            mp.start();

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("emegency");
            builder1.setMessage(alertmessage);
            alertmessage=null;
            builder1.setCancelable(true);
            builder1.setNeutralButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            mp.stop();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            //alert11.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            alert11.show();
        }
    }
}
