package com.skaleto.things.ioandnio;

import cn.hutool.socket.nio.NioClient;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author : ybyao
 * @Create : 2019-07-31 10:32
 */
@Slf4j
public class NIOClient {

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient(new InetSocketAddress(8080));

        client.setChannelHandler(i -> {
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            //从channel读数据到缓冲区
            int readBytes = i.read(readBuffer);
            if (readBytes > 0) {
                //Flips this buffer.  The limit is set to the current position and then
                // the position is set to zero，就是表示要从起始位置开始读取数据
                readBuffer.flip();
                //returns the number of elements between the current position and the  limit.
                // 要读取的字节长度
                byte[] bytes = new byte[readBuffer.remaining()];
                //将缓冲区的数据读到bytes数组
                readBuffer.get(bytes);
                log.info(new String(bytes));
            } else if (readBytes < 0) {
                i.close();
            }
        });
        client.listen();
        client.write(ByteBuffer.wrap("Hello server, this is client!".getBytes(StandardCharsets.UTF_8)));
    }

}