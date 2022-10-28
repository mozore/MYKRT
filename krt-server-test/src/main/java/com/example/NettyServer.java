package com.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    static EventLoopGroup boss = new NioEventLoopGroup();
    static EventLoopGroup worker = new NioEventLoopGroup();

    public static void main(String[] args) {

        try {
            ServerBootstrap b = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ServerChannelInitializer());
            ChannelFuture f = b.bind(4001).sync();
            System.out.println("server start done");
            f.channel().closeFuture().addListener((ChannelFutureListener) future -> {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
