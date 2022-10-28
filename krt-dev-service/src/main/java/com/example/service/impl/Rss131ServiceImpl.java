package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.api.BaseService;
import com.example.api.Rss131Service;
import com.example.pojo.entity.BaseDevice;
import com.example.pojo.entity.Rss131;
import com.example.pojo.mapper.Rss131Mapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuhaoyu
 * @since 2022-10-17
 */
@DubboService(interfaceClass = Rss131Service.class, group = "MY-YSB")
public class Rss131ServiceImpl extends ServiceImpl<Rss131Mapper, Rss131> implements Rss131Service {

    @Override
    public Rss131 getNewRecord() {
        QueryWrapper<Rss131> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit 1");
        Rss131 rss131 = getOne(wrapper);
        return rss131;
    }

    @Override
    public boolean saveRecord(Rss131 rss131) {
        boolean save = save(rss131);
        return save;
    }

    @Override
    public Rss131 parseData(byte[] data, Long devId, String location) {
        Rss131 rss131 = new Rss131();
        rss131.setDevId(devId);
        rss131.setLocation(location);
        String[] result = new String(data, StandardCharsets.UTF_8).split(",");
        rss131.setVal1(Double.parseDouble(result[0]));
        rss131.setVal2(Double.parseDouble(result[1]));
        rss131.setVal3(Double.valueOf(result[2]));
        return rss131;
    }

}
