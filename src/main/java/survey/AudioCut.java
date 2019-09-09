package survey;

import java.io.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Arrays;

/**
 * @author : ybyao
 * @Create : 2019-08-16 10:04
 */
public class AudioCut {

    private static short BITS_PER_SAMPLE = 16;

    private static short CHANNELS = 1;

    private static short BLOCK_ALIGN = 2;

    private static int AVERAGE_BYTES_PER_SECOND = 32000;


    private static int FRAME_SIZE = 576;

    private static int BIT_RATE = 40000;

    private static int SAMPLE_RATE = 16000;

    private static int TIME_PER_RATE = 36;


    public static void main(String[] args) throws IOException {

//        insertMuteAudioOfMp3();
        deleteAudioOfMp3();
    }

    /**
     * wav文件插入静音数据
     *
     * @throws IOException
     */
    public static void insertMuteAudioOfWav() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile("C:\\Users\\iflyrec\\Desktop\\ED截断分享页问题\\out\\488e2d11e8ed91cc.wav", "rw")) {
            file.seek(44 + AVERAGE_BYTES_PER_SECOND * 10);
            byte[] emptybytes = new byte[AVERAGE_BYTES_PER_SECOND * 10];
            Arrays.fill(emptybytes, (byte) 0x00);
            file.write(emptybytes);
        }
    }

    public static void insertMuteAudioOfMp3() throws IOException {
        //删除起始帧
        int fromFrame = 10000 / TIME_PER_RATE;
        //删除结束帧
        int toFrame = 20000 / TIME_PER_RATE;
        try (RandomAccessFile file = new RandomAccessFile("C:\\Users\\iflyrec\\Desktop\\音频删除调研\\out\\fd7cb883072f0b58.mp3", "rw")) {
            //每帧的字节数
            int frameByte = (FRAME_SIZE / 8 * BIT_RATE) / SAMPLE_RATE;
            //需要修改的第一帧偏移量
            int pos = 45 + (fromFrame - 1) * frameByte;
            byte[] emptyBytes = new byte[frameByte - 4];
            Arrays.fill(emptyBytes, (byte) 0x00);

            //定位到需要修改的第一个帧
            for (int i = fromFrame; i < toFrame; i++) {
                //向后偏移4个字节，不操作帧头
                pos += 4;
                file.seek(pos);
                //填入空白数据
                file.write(emptyBytes);
                //向后偏移帧数据长度
                pos += frameByte - 4;
            }
        }
    }

    public static void deleteAudioOfMp3() throws IOException {
        //删除起始帧
        int fromFrame = 10000 / TIME_PER_RATE;
        //删除结束帧
        int toFrame = 20000 / TIME_PER_RATE;
        try (RandomAccessFile file = new RandomAccessFile("C:\\Users\\iflyrec\\Desktop\\音频删除调研\\out\\fd7cb883072f0b58.mp3", "rw");
             FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\iflyrec\\Desktop\\音频删除调研\\out\\test.mp3"))) {
            //每帧的字节数
            int frameByte = (FRAME_SIZE / 8 * BIT_RATE) / SAMPLE_RATE;
            //需要修改的第一帧偏移量
            int pos = 45 + (fromFrame - 1) * frameByte;

            file.seek(0);
            //将起始帧前的数据写入新文件
            //实际操作可以使用一个buffer循环写入，避免文件过大造成性能问题
            byte[] a = new byte[pos];
            file.read(a);
            fos.write(a);

            //定位到删除结束帧
            pos += frameByte * (toFrame - fromFrame);
            file.seek(pos);
            //将后方的数据写入
            byte[] b = new byte[(int) file.length() - pos];
            file.read(b);
            fos.write(b);
        }
    }

    public static int getNumber(byte[] bytes) {
        if (bytes.length == 2) {
            return LittleEndian.getShort(bytes);
        } else {
            return LittleEndian.getInt(bytes);
        }
    }
}
