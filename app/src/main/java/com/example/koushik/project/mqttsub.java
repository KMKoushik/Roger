package com.example.koushik.project;

/**
 * Created by koushik on 19/3/17.
 */

import android.util.Log;
import java.io.PrintStream;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

class mqttSub
        implements MqttCallback
{

    mqttSub()
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
        System.out.println(mqttmessage);
    }

    public void mymqtt(String s, String s1, String s2)
    {
        MqttClient m;
        Log.v("", "im in");
        s2 = (new StringBuilder()).append("tcp://").append(s2).append(":1883").toString();
        Object obj = new MemoryPersistence();
        try
        {
            m = new MqttClient(s2, "Sending", ((org.eclipse.paho.client.mqttv3.MqttClientPersistence) (obj)));
            m.connect();
            m.setCallback(this);
            obj = new MqttMessage();
            ((MqttMessage) (obj)).setPayload(s1.getBytes());
            ((MqttMessage) (obj)).setQos(0);
            m.publish(s, ((MqttMessage) (obj)));
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception e)
        {
            System.out.println((new StringBuilder()).append("msg ").append(e.getMessage()).toString());
        System.out.println((new StringBuilder()).append("loc ").append(e.getLocalizedMessage()).toString());
        System.out.println((new StringBuilder()).append("cause ").append(e.getCause()).toString());
        System.out.println((new StringBuilder()).append("excep ").append(e).toString());
        e.printStackTrace();
        }

    }
}
