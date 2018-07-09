package com.example.koushik.project;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by koushik on 9/4/17.
 */


class mqttSub2
        implements MqttCallback
{

    mqttSub2()
    {
    }

    public void connectionLost(Throwable throwable)
    {
    }

    public void deliveryComplete(IMqttDeliveryToken imqttdeliverytoken)
    {
    }

    public void messageArrived(String s, MqttMessage mqttmessage)
            throws Exception
    {
        System.out.println("funny"+mqttmessage.toString());

       // devicepage obj=new devicepage();
        //obj.displayAlert(mqttmessage.toString());
        devicepage.alertmessage=mqttmessage.toString();
        System.out.println("we got yo"+devicepage.alertmessage);



    }

    public void mymqtt(String s)
    {
        MqttClient m;
        Log.v("", "im in");
        Object obj = new MemoryPersistence();
        try
        {
            obj = new MqttClient("tcp://"+s+":1883", "Sending", ((org.eclipse.paho.client.mqttv3.MqttClientPersistence) (obj)));
            ((MqttClient) (obj)).connect();
            ((MqttClient) (obj)).setCallback(this);
            ((MqttClient) (obj)).subscribe("danger");
            MqttMessage mqttmessage = new MqttMessage();
            mqttmessage.setPayload(s.getBytes());
            mqttmessage.setQos(0);
           // ((MqttClient) (obj)).publish("sensordata", mqttmessage);
           // return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception e)
        {
            System.out.println((new StringBuilder()).append("reason ").append(e.getMessage()).toString());
            System.out.println((new StringBuilder()).append("msg ").append(e.getMessage()).toString());
            System.out.println((new StringBuilder()).append("loc ").append(e.getLocalizedMessage()).toString());
            System.out.println((new StringBuilder()).append("cause ").append(e.getCause()).toString());
            System.out.println((new StringBuilder()).append("excep ").append(s).toString());
            e.printStackTrace();
        }
    }
        /**/
}
