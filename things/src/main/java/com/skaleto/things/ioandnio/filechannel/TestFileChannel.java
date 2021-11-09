package com.skaleto.things.ioandnio.filechannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestFileChannel {

    public static void main(String[] args) throws IOException {
        TestFileChannel testFileChannel = new TestFileChannel();
//        testFileChannel.testFileChannel();
        testFileChannel.copyFile();
    }


    public void testFileChannel() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("/Users/yaoyibin/Downloads/machine-learning-ex1/ex1.pdf", "rw");) {
            FileChannel channel = file.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);
            int read = channel.read(buf);
            while (read != -1) {

                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();
                read = channel.read(buf);
            }
        }

    }

    public void copyFile() throws IOException {
        //准备channel
        try (FileChannel sourceChannel = new FileInputStream(new File("/Users/yaoyibin/mytest/nio/source.rtf")).getChannel();
             FileChannel destChannel = new FileOutputStream(new File("/Users/yaoyibin/mytest/nio/dest.rtf")).getChannel()) {

            //准备buffer
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (sourceChannel.read(buf) != -1) {
                //切换到读模式
                buf.flip();
                destChannel.write(buf);
                buf.clear();
            }
            destChannel.force(true);
        }
    }
}
