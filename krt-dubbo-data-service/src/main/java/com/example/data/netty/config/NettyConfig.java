package com.example.data.netty.config;

import com.example.api.*;
import com.example.data.netty.NettyClient;
import com.example.data.netty.handler.YSBClientInitializer;
import com.example.pojo.entity.ActiveDevice;
import com.example.pojo.entity.BaseDevice;
import com.example.pojo.entity.DeviceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Component
public class NettyConfig {

    @DubboReference(group = "MY-YSB")
    private DeviceInfoService deviceInfoService;

    @DubboReference(group = "MY-YSB")
    private Rss131Service rss131ServiceYSB;

    @DubboReference(group = "MY-YSB")
    private Jl900Service jl900ServiceYSB;

    @DubboReference(group = "MY-YSB")
    private H3r7000Service h3r7000ServiceYSB;
    private static Bootstrap b;
    private static EventLoopGroup worker = new NioEventLoopGroup();

    private static EventLoopGroup serviceWorker = new NioEventLoopGroup();

    private static Map<String, ActiveDevice> activeDeviceMap = new ConcurrentHashMap<>();

    private static Map<String, BaseService<? extends BaseDevice>> serviceMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void nettyInit() {
        // 保存service
        serviceMap.put("RSS131", rss131ServiceYSB);
        serviceMap.put("H3R7000", h3r7000ServiceYSB);
        serviceMap.put("JL900", jl900ServiceYSB);

        // 保存在线设备信息
        List<DeviceInfo> allDeviceInfo = deviceInfoService.getAllDeviceInfo();
        for (DeviceInfo deviceInfo : allDeviceInfo) {

            ActiveDevice device = new ActiveDevice();
            device.setDeviceId(deviceInfo.getId());
            device.setLocation(deviceInfo.getLocation());
            device.setIp(deviceInfo.getIp());
            device.setPort(Integer.valueOf(deviceInfo.getPort()));
            device.setType(deviceInfo.getType());
            device.setScanTime(deviceInfo.getScanTime());
            device.setTransferLength(deviceInfo.getTransferLength());
            device.setCommand(createCommand(device.getIp(), device.getPort(), device.getType()));
            activeDeviceMap.put(device.getIp() + ":" + device.getPort(), device);
        }

        b = new Bootstrap()
                .group(worker)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new YSBClientInitializer());

        NettyClient.connectAll(b, activeDeviceMap);
    }

    private byte[] createCommand(String ip, Integer port, String type) {
        if (type.equals("JL900")) {
            return new byte[]{0x31, 0x32};
        } else if (type.equals("H3R7000")) {
            return new byte[]{0x32, 0x33};
        } else if (type.equals("RSS131")) {
            return new byte[]{0x33, 0x34};
        }
        return null;
    }


    public static EventLoopGroup getWorker() {
        return worker;
    }

    public static EventLoopGroup getServiceWorker() {
        return serviceWorker;
    }

    public static Map<String, ActiveDevice> getActiveDeviceMap() {
        return activeDeviceMap;
    }

    public static Map<String, BaseService<? extends BaseDevice>> getServiceMap() {
        return serviceMap;
    }

    public static Bootstrap getBootstrap() {
        return b;
    }
}
