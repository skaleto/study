package com.skaleto.things.survey.correlation;

import com.skaleto.things.survey.LittleEndian;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author : ybyao
 * @Create : 2019-10-22 16:34
 */
public class MyCorrelation {

    private int[][] source = null;


    /**
     * 声速(mm/s)
     */
    private static final int AUDIO_SPEED = 340000;

    /**
     * @param filePath   文件位置
     * @param start      声音开始时间点(ms)
     * @param end        声音结束时间点(ms)
     * @param micSpacing 麦克风间距(mm)
     */
    public void start(double start, double end, double micSpacing, String... filePath) throws IOException {
        AudioInfo info = getAudioInfo(filePath[0]);
        //将音频数据前后移动的窗口大小
        int windowNum = (int) (Math.ceil((micSpacing / AUDIO_SPEED) * info.getSampleRate()) + 1);
        int startSampleIndex = (int) Math.floor(start / 1000 * info.getSampleRate()) - windowNum;
        int endSampleIndex = (int) Math.ceil(end / 1000 * info.getSampleRate()) + windowNum;
        startSampleIndex = 0;
        endSampleIndex = 16000;
        int length = endSampleIndex - startSampleIndex;
        //先只处理16bit
        if (info.getBitsPerSample() == 16 && endSampleIndex > startSampleIndex) {
            source = new int[2][endSampleIndex - startSampleIndex];
            for (int count = 0; count < 2; count++) {

                try (RandomAccessFile file = new RandomAccessFile(filePath[count], "r")) {
                    //跳过44字节的头，以及开始采样点前的字节
                    file.seek(44 + startSampleIndex * 2);
                    for (int i = 0; i < length; i++) {
                        byte[] buf = new byte[2];
                        file.read(buf);
                        source[count][i] = getNumber(buf);
                    }
                }
            }

            //声达时间差计算
            List<CorrResult> TDOA_RES = tdoa(source, windowNum);
            TDOA_RES.sort(Comparator.comparingDouble(CorrResult::getCos));

            //波束形成计算
            List<CorrResult> BEAMFORMING_RES = beamForming(source, micSpacing, info.getSampleRate());
            BEAMFORMING_RES.sort(Comparator.comparingDouble(CorrResult::getCos));

            System.out.println(BEAMFORMING_RES.get(BEAMFORMING_RES.size() - 1).getAngle() * 180 / Math.PI);


            /*
             * Result...
             */
            int closerMicIndex = 0;
            if (TDOA_RES.get(TDOA_RES.size() - 1).getMatrixOffset() > 0) {
                closerMicIndex = 1;
            } else if (TDOA_RES.get(TDOA_RES.size() - 1).getMatrixOffset() == 0) {
                closerMicIndex = -1;
            }

            System.err.println("Audio source close to mic: " + closerMicIndex);

            double sampleNum = (double) TDOA_RES.get(TDOA_RES.size() - 1).getMatrixOffset();
            double theta_pi = Math.acos((sampleNum / info.getSampleRate() * AUDIO_SPEED) / micSpacing);
            double theta = theta_pi * 180 / Math.PI;

            System.err.println("Probable angle: " + theta + "°");

            plot(micSpacing, 100, theta_pi, 300);
        }
    }

    /**
     * 以双麦克风M1,M2中点为原点，在麦克风平面内垂直于直线M1M2的直线为x轴，M1M2直线为y轴，垂直于麦克风平面的直线为z轴建立坐标系
     *
     * @param micSpacing 双麦克风间距
     * @param planeDis   平行于麦克风平面的垂直距离
     * @param theta      音源与较远麦克风的夹角
     * @param sourceDis  音源与较远麦克风的直线距离
     */
    public void plot(double micSpacing, double planeDis, double theta, double sourceDis) {

        double source_X = Math.sqrt(Math.pow(sourceDis * Math.sin(theta), 2) - Math.pow(planeDis, 2));

        double source_Y = sourceDis * Math.cos(theta) - micSpacing / 2;

        double source_Z = planeDis;

        System.err.println("Audio source is at [ " + source_X + "," + source_Y + "," + source_Z + "]");
        System.err.println("             or at [ " + -source_X + "," + source_Y + "," + source_Z + "]");

    }

    public List<CorrResult> tdoa(int[][] source, int windowNum) {
        List<CorrResult> res = new ArrayList<>();
        for (int i = -windowNum; i <= windowNum; i++) {
            double innerProduct = 0;
            double vectorNormA = 0;
            double vectorNormB = 0;
            for (int j = Math.abs(i); j < source[0].length - Math.abs(i); j++) {
                innerProduct += source[0][j] * source[1][j - i];
                vectorNormA += Math.pow(source[0][j], 2);
                vectorNormB += Math.pow(source[1][j - i], 2);
            }

            double cos = innerProduct / (Math.sqrt(vectorNormA) * Math.sqrt(vectorNormB));

            System.out.println("Offset：" + i + " inner: " + innerProduct + " cos: " + cos);

            res.add(new CorrResult()
                    .setMatrixOffset(i)
                    .setInner(innerProduct)
                    .setCos(cos));

        }

        return res;
    }

    /**
     * 假定夹角小于90度
     *
     * @param source
     * @param micSpacing
     * @return
     */
    public List<CorrResult> beamForming(int[][] source, double micSpacing, double sampleRate) {
        List<CorrResult> res = new ArrayList<>();
        for (double i = 0; i <= Math.PI / 2; i += Math.PI / 180) {
            double delta_t = micSpacing * Math.cos(i) / AUDIO_SPEED;
            int sampleNum = (int) Math.floor(delta_t * sampleRate);

            double innerProduct = 0;
            double vectorNormA = 0;
            double vectorNormB = 0;

            for (int j = sampleNum; j < source[0].length - sampleNum; j++) {
                innerProduct += source[0][j] * source[1][j - sampleNum];
                vectorNormA += Math.pow(source[0][j], 2);
                vectorNormB += Math.pow(source[1][j - sampleNum], 2);
            }

            double cos = innerProduct / (Math.sqrt(vectorNormA) * Math.sqrt(vectorNormB));

            System.out.println("angle：" + i + " inner: " + innerProduct + " cos: " + cos);


            res.add(new CorrResult()
                    .setAngle(i)
                    .setInner(innerProduct)
                    .setCos(cos));
        }

        return res;
    }

    public AudioInfo getAudioInfo(String filePath) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            int numChannels = getFigure(file, 22, 2);
            int sampleRate = getFigure(file, 24, 4);
            int byteRate = getFigure(file, 28, 4);
            int bitsPerSample = getFigure(file, 34, 2);

            return new AudioInfo()
                    .setNumChannels(numChannels)
                    .setSampleRate(sampleRate)
                    .setByteRate(byteRate)
                    .setBitsPerSample(bitsPerSample);
        }
    }

    public static int getFigure(RandomAccessFile file, int offset, int length) throws IOException {
        if (file != null) {
            byte[] buf = new byte[length];
            file.seek(offset);
            file.read(buf);
            return getNumber(buf);
        }

        return 0;
    }

    public static int getNumber(byte[] bytes) {
        if (bytes.length == 2) {
            return LittleEndian.getShort(bytes);
        } else {
            return LittleEndian.getInt(bytes);
        }
    }

    public static void main(String[] args) throws IOException {
        new MyCorrelation().start(0, 1000, 100, "C:\\Users\\iflyrec\\Documents\\WeChat Files\\yyb-weixin\\FileStorage\\File\\2019-10\\audio\\audio\\channel-0.wav", "C:\\Users\\iflyrec\\Documents\\WeChat Files\\yyb-weixin\\FileStorage\\File\\2019-10\\audio\\audio\\channel-3.wav");
//        new MyCorrelation().getAudioInfo("C:\\Users\\iflyrec\\Documents\\WeChat Files\\yyb-weixin\\FileStorage\\File\\2019-10\\audio\\audio\\channel-0.wav");

//        new MyCorrelation().test("C:\\Users\\iflyrec\\Documents\\WeChat Files\\yyb-weixin\\FileStorage\\File\\2019-10\\audio\\audio\\channel-0.wav");

//        System.out.println(getNumber(new byte[]{(byte) 0x61, (byte) 0xff}));
    }

    private void test(String filePath) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            //跳过44字节的头，以及开始采样点前的字节
            file.seek(44);
            byte[] buf = new byte[1024];
            file.read(buf);

            int[] temp = new int[512];

            for (int i = 0; i < 512; i += 2) {
                byte[] t = new byte[2];
                t[0] = buf[i];
                t[1] = buf[i + 1];
                temp[i / 2] = getNumber(t);
            }

            System.out.println(buf.length);
        }
    }
}
