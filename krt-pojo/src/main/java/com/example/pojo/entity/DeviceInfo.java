package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuhaoyu
 * @since 2022-10-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("device_info")
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String type;

    private String location;

    private String ip;

    private String port;

    private Integer scanTime;

    private Integer transferLength;

}
