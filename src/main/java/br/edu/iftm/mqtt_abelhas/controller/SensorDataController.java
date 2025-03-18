package br.edu.iftm.mqtt_abelhas.controller;

import br.edu.iftm.mqtt_abelhas.entity.SensorData;
import br.edu.iftm.mqtt_abelhas.repository.SensorDataRepository;
import br.edu.iftm.mqtt_abelhas.service.SensorDataService; 
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SensorDataController {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private SensorDataService mqttService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/publish")
    public ResponseEntity<String> publishData(@RequestBody SensorData sensorData) throws JsonProcessingException {
        
        sensorDataRepository.save(sensorData);
        System.out.println("Stored SensorData: " + sensorData);

            try {
                String jsonString = objectMapper.writeValueAsString(sensorData);
                System.out.println("Publishing JSON: " + jsonString);
                mqttService.publish("test/iftm/gabriel", jsonString);
            } catch (MqttException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Data saved but failed to publish via MQTT");
            }

        return ResponseEntity.ok("Data stored successfully in MySQL!");
    }
}
