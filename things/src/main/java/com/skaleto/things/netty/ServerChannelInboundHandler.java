package com.skaleto.things.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @author : ybyao
 * @Create : 2019-07-31 17:54
 */
/**
 * Sharable注解用来标识这个Handler的实例可以被多个通道安全地共享（没有与特定通道强相关的数据或状态，相对比较抽象的）
 * 由于在一个通道里，所有handler由一个线程来处理，所以一个通道里不存在多线程的问题；
 * 但当需要支持多个通道共享实例的时候，如果不加这个注解就会抛出异常，加了这个注解就意味着你需要特别关注这些Handler中的多线程问题
 */
@ChannelHandler.Sharable
public class ServerChannelInboundHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf buf = (ByteBuf) msg;
        //因为本例中我们使用的是netty4.1，因此默认的ByteBuf是Direct ByteBuf，因此无法直接通过buffer的array属性拿到数据
        //需要调用一次readBytes来获得真实的数据
        byte[] reg = new byte[buf.readableBytes()];
        buf.readBytes(reg);

        String body = new String(reg, "UTF-8");
        System.out.println(Thread.currentThread().getName() + ",The server receive  order : " + body);

        /**回复消息
         * copiedBuffer：创建一个新的缓冲区，内容为里面的参数
         * 通过 ChannelHandlerContext 的 write 方法将消息异步发送给客户端
         * */
        String respMsg = "I am Server，消息接收 success!";
        ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
        //虽然是异步操作，但还可以通过future拿到处理完成的结果
        ChannelFuture writeFuture = ctx.write(respByteBuf);
        writeFuture.addListener((ChannelFuture listener) -> {
            System.out.println("写入完毕");
        });
        ctx.flush();
    }

}
