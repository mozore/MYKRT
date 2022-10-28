package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDevice implements Serializable {
    private static final long serialVersionUID = 80086L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String location;

    private Long devId;


    public BaseDevice(String location, Long devId) {
        this.location = location;
        this.devId = devId;
    }
}
