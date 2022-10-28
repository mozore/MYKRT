package com.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        System.out.println("connect to adress : " + ch.remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SocketChannel ch = (SocketChannel) ctx.channel();
        NettyServer.worker.scheduleAtFixedRate(() -> {
            ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer();
            String s = "1,2";
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            buf.writeBytes(bytes);
            ctx.writeAndFlush(buf);
        }, 0, 5, TimeUnit.SECONDS);
    }
}
