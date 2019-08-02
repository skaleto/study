package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : ybyao
 * @Create : 2019-07-31 14:58
 */
public class NettyServer {

    public static void main(String[] args) {

        //MainReactor
        NioEventLoopGroup mainReactor = new NioEventLoopGroup();
        //工作线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                //组装group
                .group(mainReactor, workerGroup)
                //设置channel类型为NIO类型
                .channel(NioServerSocketChannel.class)
                //设置连接配置参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                //配置入站、出站事件handler
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new ServerChannelInboundHandler());
                    }
                });

        bootstrap.bind(9999).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口绑定成功");
            } else {
                System.out.println("端口绑定失败");
            }
        });


    }
}
