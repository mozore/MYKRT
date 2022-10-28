package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.api.DeviceInfoService;
import com.example.pojo.entity.DeviceInfo;
import com.example.pojo.mapper.DeviceInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuhaoyu
 * @since 2022-10-17
 */
@DubboService(interfaceClass = DeviceInfoService.class, group = "MY-YSB")
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements DeviceInfoService {

    @Override
    public List<DeviceInfo> getAllDeviceInfo() {
        return list();
    }

    @Override
    public DeviceInfo getDeviceInfoByType(String type) {
        QueryWrapper<DeviceInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        DeviceInfo deviceInfo = getOne(wrapper);
        return deviceInfo;
    }

    @Override
    public DeviceInfo getDeviceInfoByAdress(String ip, int port) {
        QueryWrapper<DeviceInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("ip", ip)
                .eq("port", port);
        DeviceInfo deviceInfo = getOne(wrapper);
        return deviceInfo;
    }
}
