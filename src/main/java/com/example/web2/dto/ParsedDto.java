package com.example.web2.dto;

import lombok.Data;

@Data
public class ParsedDto {
    private String rssi;
    private String gatewayId;
    private String freq;
    private String data;
    private String time;
}
