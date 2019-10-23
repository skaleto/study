package survey.correlation;

import javafx.embed.swt.SWTFXUtils;
import survey.LittleEndian;

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
     * @param filePath    文件位置
     * @param start       声音开始时间点(ms)
     * @param end         声音结束时间点(ms)
     * @param micDistance 麦克风间距(mm)
     */
    public void start(double start, double end, double micDistance, String... filePath) throws IOException {
        AudioInfo info = getAudioInfo(filePath[0]);
        //将音频数据前后移动的窗口大小
        int windowNum = (int) (Math.ceil((micDistance / AUDIO_SPEED) * info.getSampleRate()) + 1);
        int startSampleIndex = (int) Math.floor(start / 1000 * info.getSampleRate()) - windowNum;
        int endSampleIndex = (int) Math.ceil(end / 1000 * info.getSampleRate()) + windowNum;
        int length = endSampleIndex - startSampleIndex;
        //先只处理16bit
        if (info.getBitsPerSample() == 16 && endSampleIndex > startSampleIndex && startSampleIndex > 0) {
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

            List<CorrResult> results = calculate(source, windowNum);
            results.sort(Comparator.comparingDouble(CorrResult::getCos));

            int closerMicIndex = 0;
            if (results.get(results.size() - 1).getMatrixOffset() > 0) {
                closerMicIndex = 1;
            } else if (results.get(results.size() - 1).getMatrixOffset() == 0) {
                closerMicIndex = -1;
            }

            System.err.println("Audio source close to mic: " + closerMicIndex);

            double sampleNum = (double) results.get(results.size() - 1).getMatrixOffset();
            double theta = Math.acos((sampleNum / info.getSampleRate() * AUDIO_SPEED) / micDistance);
            theta = theta * 180 / Math.PI;

            System.err.println("Probable angle: " + theta + "°");
        }
    }

    public List<CorrResult> calculate(int[][] source, int windowNum) {
        List<CorrResult> res = new ArrayList<>();
        for (int i = -windowNum; i <= windowNum; i++) {
            double innerProduct = 0;
            double vectorNormA = 0;
            double vectorNormB = 0;
            for (int j = windowNum; j < source[0].length - windowNum; j++) {
                innerProduct += source[0][j] * source[1][j - i];
                vectorNormA += Math.pow(source[0][j], 2);
                vectorNormB += Math.pow(source[1][j - i], 2);
            }

            double cos = innerProduct / (Math.sqrt(vectorNormA) * Math.sqrt(vectorNormB));

            res.add(new CorrResult()
                    .setMatrixOffset(i)
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
        new MyCorrelation().start(1704, 1714, 100, "C:\\Users\\iflyrec\\Documents\\WeChat Files\\yyb-weixin\\FileStorage\\File\\2019-10\\audio\\audio\\channel-0.wav", "C:\\Users\\iflyrec\\Documents\\WeChat Files\\yyb-weixin\\FileStorage\\File\\2019-10\\audio\\audio\\channel-3.wav");
//        new MyCorrelation().getAudioInfo("C:\\Users\\iflyrec\\Documents\\WeChat Files\\yyb-weixin\\FileStorage\\File\\2019-10\\audio\\audio\\channel-0.wav");

    }
}
