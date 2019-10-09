package ioandnio.filechannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestFileChannel {

    public static void main(String[] args) throws IOException {
        TestFileChannel testFileChannel = new TestFileChannel();
        testFileChannel.testFileChannel();
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
}
