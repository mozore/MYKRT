package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuhaoyu
 * @since 2022-10-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class H3r7000 extends BaseDevice implements Serializable {

    private static final long serialVersionUID = 1101112L;

    private Double val1;

    private Double val2;

    private Double val3;

    private Double var4;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
