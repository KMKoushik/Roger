package com.example.koushik.project;

/**
 * Created by koushik on 19/3/17.
 */

import android.util.Log;
import android.widget.Toast;

import java.io.PrintStream;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

// Referenced classes of package com.example.rehulanthony.avessmarthub:
//            devicepage

class mqttSub1
        implements MqttCallback
{

    mqttSub1()
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

        sensors.message = mqttmessage.toString();
        System.out.println(sensors.message);

        sensors.sensorvalues=sensors.message.split(",");

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
            ((MqttClient) (obj)).subscribe("sensorreply");
            MqttMessage mqttmessage = new MqttMessage();
            mqttmessage.setPayload(s.getBytes());
            mqttmessage.setQos(0);
            ((MqttClient) (obj)).publish("sensordata", mqttmessage);
            return;
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


