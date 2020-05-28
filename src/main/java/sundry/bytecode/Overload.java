package sundry.bytecode;

import java.io.Serializable;

public class Overload {                                // 全部不注释，输出：hello, char

    public static void sayHello(char arg) {            // 1.注释掉，输出：hello, int
        System.out.println("hello, char");
    }

    public static void sayHello(int arg) {            // 2.注释掉，输出：hello, long
        System.out.println("hello, int");
    }

    public static void sayHello(long arg) {            // 3.注释掉，输出：Character
        System.out.println("hello, long");
    }

    public static void sayHello(Character arg) {    // 4，注释掉，输出：Serializable
        System.out.println("hello, Character");
    }

    public static void sayHello(Serializable arg) {    // 5.注释掉，输出：hello, Object
        System.out.println("hello, Serializable");
    }

    public static void sayHello(Object arg) {        // 6.注释掉，输出：hello, char...
        System.out.println("hello, Object");
    }

    public static void sayHello(char... arg) {        // 7.不能注释掉
        System.out.println("hello, char...");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}