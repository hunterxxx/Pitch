package com.example.hunter.pitch;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by hunter on 14.02.2016.
 */
public class Publish {
    public void main() {

        String topic = "/v1/2aa20b73-9362-43a6-97ba-c3730b326283/data";
        String content = "{ \"meaning\" : \"smoke\", \"value\":\"ALARM\"}";
        int qos = 2;
        String broker = "tcp://mqtt.relayr.io";
        String clientId = "TKqILc5NiQ6aXusNzCzJigw";
        String MQTT_PASSWORD = "LgIE1MxE8kOK";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setPassword(MQTT_PASSWORD.toCharArray());
            connOpts.setUserName("2aa20b73-9362-43a6-97ba-c3730b326283");
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
