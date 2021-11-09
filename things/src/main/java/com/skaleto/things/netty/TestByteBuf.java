package com.skaleto.things.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class TestByteBuf {

    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        IntStream.range(0, buffer.readableBytes()).forEach(i -> {
            log.info("" + buffer.getByte(i));
        });

        while(buffer.isReadable()){
            log.info("read"+buffer.readByte());
        }
    }
}
