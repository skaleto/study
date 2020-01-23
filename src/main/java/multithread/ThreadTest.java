package multithread;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    public void test(){
        Thread t=new Thread();
        t.start();
        t.run();
        t.isAlive();


    }


}
