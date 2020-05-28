package sundry;

// Run with
// java -XX:+PrintGC -XX:+DoEscapeAnalysis EscapeTest

import java.util.stream.IntStream;

public class EscapeTest {

    private static Object o=new Object();

    public static void main(String[] args) {
//        ArrayList<Object> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i);
//        }
//        for (int i = 0; i < 400_000_000; i++) {
//            forEach(list, obj -> {
//            });
//        }

//        float a= (float) (10/(1.0-1.0));
//        System.out.println(a);

        int x = 2;

        synchronized (o){
            IntStream.of(1, 2, 3).map(i -> i * 2).map(i -> i * x);
        }

    }
}
