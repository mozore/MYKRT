package com.example.data.netty.handler;

import com.example.api.BaseService;
import com.example.data.netty.config.NettyConfig;
import com.example.data.netty.NettyClient;
import com.example.pojo.entity.ActiveDevice;
import com.example.pojo.entity.BaseDevice;
import com.example.pojo.entity.Rss131;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


@Slf4j
@ChannelHandler.Sharable
public class YSBClientHandler extends ChannelInboundHandlerAdapter {


    /**
     * 通道活跃时调用，发送命令
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        String ip = ch.remoteAddress().getAddress().getHostAddress();
        int port = ch.remoteAddress().getPort();
        log.info("connect : {}:{}", ip, port);
        byte[] command = NettyConfig.getActiveDeviceMap().get(ip + ":" + port).getCommand();
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer();
        buf.writeBytes(command);
        ctx.writeAndFlush(buf);

        // super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        String ip = ch.remoteAddress().getAddress().getHostAddress();
        int port = ch.remoteAddress().getPort();
        log.info("not connect : {}:{}", ip, port);
        ActiveDevice device = NettyConfig.getActiveDeviceMap().get(ip + ":" + port);
        ctx.channel().close();
        NettyClient.connect(NettyConfig.getBootstrap(), device, 3);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        String ip = ch.remoteAddress().getAddress().getHostAddress();
        int port = ch.remoteAddress().getPort();

        // 查询活跃设备信息
        ActiveDevice activeDevice = NettyConfig.getActiveDeviceMap().get(ip + ":" + port);

        ByteBuf buf = (ByteBuf) msg;
        if (buf.readableBytes() < activeDevice.getTransferLength()) {
            return;
        }

        // 读取单条数据并启动新的线程解析
        byte[] msgByte = new byte[activeDevice.getTransferLength()];
        buf.readBytes(msgByte);
        EventLoopGroup serviceWorker = NettyConfig.getServiceWorker();
        serviceWorker.execute(() -> {
            BaseService<? extends BaseDevice> baseService = NettyConfig.getServiceMap().get(activeDevice.getType());
            BaseDevice baseDevice = baseService.parseData(msgByte, activeDevice.getDeviceId(), activeDevice.getLocation());
            // ctx.writeAndFlush(baseDevice);
            ctx.fireChannelRead(baseDevice);
        });
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                ctx.channel().close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        String ip = ch.remoteAddress().getAddress().getHostAddress();
        int port = ch.remoteAddress().getPort();
        log.info("exceptionCaught  : {}:{}", ip, port);
        ctx.channel().close();
        // super.exceptionCaught(ctx, cause);
    }
}
