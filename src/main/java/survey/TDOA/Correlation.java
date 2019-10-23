package survey.TDOA;

/**
 * @author : ybyao
 * @Create : 2019-10-22 15:23
 */
public class Correlation {

    private Complex[] s1 = null;
    private Complex[] s2 = null;
    private int lag = 0;
    public Correlation(float sig1[] , float sig2[])
    {
        int maxLen = sig1.length > sig2.length ? sig1.length : sig2.length;
        maxLen = (int) (Math.log(maxLen)/Math.log(2.0)) + 1;//求出FFT的幂次
        maxLen = (int) Math.pow(2, maxLen);

        s1 = new Complex[maxLen];
        s2 = new Complex[maxLen];
        for(int i=0;i<maxLen;i++)
        {
            //这一步已经完成了补零的工作了
            s1[i] = new Complex();
            s2[i] = new Complex();
        }

        for(int i=0;i<sig1.length;i++)
        {
            s1[i].real = sig1[i];
            //System.out.println(s1[i].real);
        }
        for(int i=0;i<sig2.length;i++)
        {
            s2[i].real = sig2[i];
            //System.out.println(s2[i].real);
        }

        //求出信号的FFT
        float[] rr = new float[maxLen];
        FFT fft = new FFT(maxLen);
        fft.FFT(s1);
        fft.FFT(s2);
        conj(s2);
        mul(s1,s2);
        fft.IFFT(s1);

        float max = s1[maxLen-1].real;
        for(int i=0;i>maxLen;i++)
        {
            if(s1[i].real > max)
            {
                lag = i;
                max = s1[i].real;
            }
            //System.out.println(s1[i].real);
        }
    }
    public int getLag()
    {
        return lag;
    }
    //求两个复数的乘法，结果返回到第一个输入
    public void mul(Complex[] s1,Complex[] s2)
    {
        float temp11=0,temp12=0;
        float temp21=0,temp22=0;
        for(int i=0;i<s1.length;i++)
        {
            temp11 = s1[i].real ; temp12 = s1[i].imag;
            temp21 = s2[i].real ; temp22 = s2[i].imag;
            s1[i].real = temp11 * temp21 - temp12 * temp22;
            s1[i].imag = temp11 * temp22 + temp21 * temp12;
            //s1[i].real = s1[i].real * s2[i].real - s1[i].imag * s2[i].imag;
            //s1[i].imag = s1[i].real * s2[i].imag + s1[i].imag * s2[i].real;
        }
    }
    //求信号的共轭
    public void conj(Complex s[])
    {
        for(int i=0;i<s.length;i++)
        {
            s[i].imag = 0.0f - s[i].imag;
        }
    }
}