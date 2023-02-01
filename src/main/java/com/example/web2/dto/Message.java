package com.example.web2.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "seq")
    @JsonIgnore
    private Long id;
    private String devEui;
    private String rssi;
    private String gatewayId;
    private String freq;
    private String codeCmd;
    private String nomerShetchika;
    private String potreblenie;
    private String obratniyPotok;
    private String statusShetchika;
    private String statusAlarm;
    private String voltageLevel;
    private String signal;
    private String snr;

    private String date;

    public Message(String devEui, String rssi, String gatewayId, String freq, String codeCmd, String nomerShetchika, String potreblenie, String obratniyPotok, String statusShetchika, String statusAlarm, String voltageLevel, String signal, String snr, String date) {
        this.devEui = devEui;
        this.rssi = rssi;
        this.gatewayId = gatewayId;
        this.freq = freq;
        this.codeCmd = codeCmd;
        this.nomerShetchika = nomerShetchika;
        this.potreblenie = potreblenie;
        this.obratniyPotok = obratniyPotok;
        this.statusShetchika = statusShetchika;
        this.statusAlarm = statusAlarm;
        this.voltageLevel = voltageLevel;
        this.signal = signal;
        this.snr = snr;
        this.date = date;
    }

    public Message() {

    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", devEui='" + devEui + '\'' +
                ", rssi='" + rssi + '\'' +
                ", gatewayId='" + gatewayId + '\'' +
                ", freq='" + freq + '\'' +
                ", codeCmd='" + codeCmd + '\'' +
                ", nomerShetchika='" + nomerShetchika + '\'' +
                ", potreblenie='" + potreblenie + '\'' +
                ", obratniyPotok='" + obratniyPotok + '\'' +
                ", statusShetchika='" + statusShetchika + '\'' +
                ", statusAlarm='" + statusAlarm + '\'' +
                ", voltageLevel='" + voltageLevel + '\'' +
                ", signal='" + signal + '\'' +
                ", snr='" + snr + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
