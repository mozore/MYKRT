package com.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ServerHandler());
    }
}
