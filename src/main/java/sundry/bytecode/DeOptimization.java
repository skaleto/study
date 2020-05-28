package sundry.bytecode;

public class DeOptimization {

    public static int test(boolean a, int b) {
        int i;
        if (a) {
            i = b;
        } else {
            i = b + 1;
        }
        return i;
    }

    public static boolean flag = true;
    public static int value0 = 0;
    public static int value1 = 1;

//    public static int foo(int value) {
//        int result = bar(flag);
//        if (result != 0) {
//            return result;
//        } else {
//            return value;
//        }
//    }
//
//    public static int bar(boolean flag) {
//        return flag ? value0 : value1;
//    }

    public static int foo(int value) {
        int result = flag ? value0 : value1;
        if (result != 0) {
            return result;
        } else {
            return value;
        }
    }


    public void testEscapeWithLock() {
        synchronized (new Object()) {
            System.out.println("1");
        }
    }

//    public int scalarReplacement() {
//        ScalarExample se = new ScalarExample();
//        se.x = 1;
//        se.y = 2;
//        return se.y + 2;
//    }

    public int scalarReplacement() {
        int y = 2;
        return y + 2;
    }

    public class ScalarExample {
        private int x;
        private int y;
    }
}
