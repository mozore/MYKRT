package com.example.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.entity.DeviceInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuhaoyu
 * @since 2022-10-17
 */
public interface DeviceInfoService extends IService<DeviceInfo> {
    /**
     * 查询所有当前设备信息
     * @return
     */
    List<DeviceInfo> getAllDeviceInfo();

    /**
     * 根据类型查找设备
     * @param type
     * @return
     */
    DeviceInfo getDeviceInfoByType(String type);

    /**
     * 根据IP和PORT查找设备信息
     * @param ip
     * @param port
     */
    DeviceInfo getDeviceInfoByAdress(String ip, int port);
}
