package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.api.BaseService;
import com.example.api.H3r7000Service;
import com.example.api.Jl900Service;
import com.example.pojo.entity.BaseDevice;
import com.example.pojo.entity.H3r7000;
import com.example.pojo.entity.Rss131;
import com.example.pojo.mapper.H3r7000Mapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuhaoyu
 * @since 2022-10-24
 */
@DubboService(interfaceClass = H3r7000Service.class, group = "MY-YSB")
public class H3r7000ServiceImpl extends ServiceImpl<H3r7000Mapper, H3r7000> implements H3r7000Service {
    @Override
    public H3r7000 getNewRecord() {
        QueryWrapper<H3r7000> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit 1");
        H3r7000 h3r7000 = getOne(wrapper);
        return h3r7000;
    }

    @Override
    public boolean saveRecord(H3r7000 h3r7000) {
        boolean save = save(h3r7000);
        return save;
    }

    @Override
    public H3r7000 parseData(byte[] data, Long devId, String location) {
        H3r7000 h3r7000 = new H3r7000();
        h3r7000.setDevId(devId);
        h3r7000.setLocation(location);
        String[] result = new String(data, StandardCharsets.UTF_8).split(",");
        h3r7000.setVal1(Double.parseDouble(result[0]));
        h3r7000.setVal2(Double.parseDouble(result[1]));
        h3r7000.setVal3(Double.valueOf(result[2]));
        return h3r7000;
    }

}
