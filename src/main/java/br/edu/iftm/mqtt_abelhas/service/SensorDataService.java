package br.edu.iftm.mqtt_abelhas.service;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.UUID;

@Service
public class SensorDataService {

    private MqttAsyncClient mqttClient;


    public void publish(String topic, String payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(2);
        message.setRetained(true);
        mqttClient.publish(topic, message);
        System.out.println("Published to MQTT topic: " + topic);
    }
}
