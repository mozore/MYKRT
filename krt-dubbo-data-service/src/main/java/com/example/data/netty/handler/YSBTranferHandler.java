package com.example.data.netty.handler;

import com.example.api.BaseService;
import com.example.data.netty.config.NettyConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class YSBTranferHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String type = msg.getClass().getSimpleName().toUpperCase();
        BaseService baseService = NettyConfig.getServiceMap().get(type);
        baseService.saveRecord(msg);
    }
}
