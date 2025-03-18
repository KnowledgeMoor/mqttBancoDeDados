package br.edu.iftm.mqtt_abelhas.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("num_saidas")
    @Column(name = "num_saidas")
    private int numSaidas;

    @JsonProperty("num_entradas")
    @Column(name = "num_entradas")
    private int numEntradas;

    @JsonProperty("humidade_interna")
    @Column(name = "humidade_interna")
    private double humidadeInterna;

    @JsonProperty("humidade_externa")
    @Column(name = "humidade_externa")
    private double humidadeExterna;

    @JsonProperty("temperatura_interna")
    @Column(name = "temperatura_interna")
    private double temperaturaInterna;

    @JsonProperty("temperatura_externa")
    @Column(name = "temperatura_externa")
    private double temperaturaExterna;

    @JsonProperty("luminosidade")
    @Column(name = "luminosidade")
    private double luminosidade;

    @JsonProperty("dataHora")
    @Column(name = "dataHora")
    private String dataHora; // You can change this to a Date/LocalDateTime if you format the date appropriately.

    // Constructors
    public SensorData() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public int getNumSaidas() {
        return numSaidas;
    }

    public void setNumSaidas(int numSaidas) {
        this.numSaidas = numSaidas;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public double getHumidadeInterna() {
        return humidadeInterna;
    }

    public void setHumidadeInterna(double humidadeInterna) {
        this.humidadeInterna = humidadeInterna;
    }

    public double getHumidadeExterna() {
        return humidadeExterna;
    }

    public void setHumidadeExterna(double humidadeExterna) {
        this.humidadeExterna = humidadeExterna;
    }

    public double getTemperaturaInterna() {
        return temperaturaInterna;
    }

    public void setTemperaturaInterna(double temperaturaInterna) {
        this.temperaturaInterna = temperaturaInterna;
    }

    public double getTemperaturaExterna() {
        return temperaturaExterna;
    }

    public void setTemperaturaExterna(double temperaturaExterna) {
        this.temperaturaExterna = temperaturaExterna;
    }

    public double getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(double luminosidade) {
        this.luminosidade = luminosidade;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}