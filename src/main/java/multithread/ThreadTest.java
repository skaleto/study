package multithread;

public class ThreadTest {

    public void test(){
        Thread t=new Thread();
        t.start();
        t.run();
        t.isAlive();
        t.suspend();
    }


}
