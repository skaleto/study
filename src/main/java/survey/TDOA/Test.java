package survey.TDOA;

/**
 * @author : ybyao
 * @Create : 2019-10-22 15:24
 */
public class Test {

    public static void main(String args[])
    {
        //fft的测试函数代码
		/*int f0 = 10;
		int fs = 100;
		int FFT_LENGHT = 512;
		float[] testdata = new float[FFT_LENGHT];
		Complex[] r = new Complex[FFT_LENGHT*2];
		for(int i=0;i<r.length;i++)
		{
			r[i] = new Complex();
		}
		FFT fft = new FFT(FFT_LENGHT*2);

		for(int i=0;i<FFT_LENGHT;i++)
		{
			r[i].real = (float) Math.sin(2*3.1415926*i*f0/fs);
			r[i].imag = 0.0f;
			//testdata[i] = (float) Math.sin(2*3.1415926*i*f0/fs);
		}
		//r = fft.complexLization(testdata);
		fft.FFT(r);
		testdata = fft.magnitude(r);
		for(int i=0;i<FFT_LENGHT;i++)
		{
			System.out.println(testdata[i]);
		}*/

        //测试互相关函数

        int f0=10;
        int fs=150;
        int K = 50;
        int lag = 50;
        int dataLength = 16;
        float[] s1 = new float[dataLength];
        float[] s2 = new float[dataLength*2];

        for(int i=0;i<dataLength;i++)
        {
            //s1[i] = (float) Math.sin(2*3.14*(i*2.0/K/fs+f0)*i/fs);
            s1[i] = (float) Math.sin(2*3.14*f0*i/fs);
            //System.out.println(s1[i]);
        }
        for(int i=0;i<dataLength*1;i++)
        {
            s2[i] = 0;
        }
        for(int i=dataLength*1;i<dataLength*2;i++)
        {
            s2[i] = s1[i-dataLength*1];
        }
        Correlation correlation = new Correlation(s1, s2);
        System.out.println("结果："+correlation.getLag());
    }
}
