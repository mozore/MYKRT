package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.api.BaseService;
import com.example.api.Jl900Service;
import com.example.pojo.entity.BaseDevice;
import com.example.pojo.entity.Jl900;
import com.example.pojo.mapper.Jl900Mapper;
import org.apache.dubbo.config.annotation.DubboService;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuhaoyu
 * @since 2022-10-17
 */
@DubboService(interfaceClass = Jl900Service.class, group = "MY-YSB")
public class Jl900ServiceImpl extends ServiceImpl<Jl900Mapper, Jl900> implements Jl900Service {

    @Override
    public Jl900 getNewRecord() {
        QueryWrapper<Jl900> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit 1");
        Jl900 jl900 = getOne(wrapper);
        return jl900;
    }

    @Override
    public boolean saveRecord(Jl900 jl900) {
        boolean save = save(jl900);
        return save;
    }

    @Override
    public Jl900 parseData(byte[] data, Long devId, String location) {
        Jl900 jl900 = new Jl900();
        jl900.setLocation(location);
        jl900.setDevId(devId);
        String[] result = new String(data, StandardCharsets.UTF_8).split(",");
        jl900.setVal1(Double.parseDouble(result[0]));
        jl900.setVal2(Double.parseDouble(result[1]));
        return jl900;
    }

}
