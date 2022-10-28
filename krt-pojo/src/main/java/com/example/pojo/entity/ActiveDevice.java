package com.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveDevice {
    private Long deviceId;
    private String location;
    private String ip;
    private Integer port;
    private String type;
    private Integer scanTime;
    private byte[] command;
    private Integer transferLength;
}
