package com.example.data.netty;


import com.example.data.netty.config.NettyConfig;
import com.example.pojo.entity.ActiveDevice;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NettyClient {

    public static void connectAll(Bootstrap b, Map<String, ActiveDevice> activeDeviceMap) {
        EventLoopGroup serviceWorker = NettyConfig.getServiceWorker();
        for (ActiveDevice activeDevice : activeDeviceMap.values()) {
            EventLoop next = serviceWorker.next();
            next.execute(() -> connect(b, activeDevice, 3));

        }
    }

    public static void connect(Bootstrap b, ActiveDevice activeDevice, Integer time) {
        b.connect(activeDevice.getIp(), activeDevice.getPort())
                .addListener((ChannelFutureListener) future -> {
                    final EventLoop eventLoop = future.channel().eventLoop();
                    if (!future.isSuccess()) {
                        log.error("{} {}:{}-{}连接失败, {}秒后重试",
                                new Date(),
                                activeDevice.getIp(),
                                activeDevice.getPort(),
                                activeDevice.getType(),
                                time);
                        if (time == 720) {
                            future.channel().close();
                            return;
                        }
                        eventLoop.schedule(() -> connect(b, activeDevice, time * 2), time, TimeUnit.SECONDS);
                    }
                });
    }

}
