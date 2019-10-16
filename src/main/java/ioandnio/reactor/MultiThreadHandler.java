package ioandnio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : ybyao
 * @Create : 2019-10-10 16:53
 */
public class MultiThreadHandler implements Runnable {

    private final SocketChannel channel;

    private final SelectionKey sk;

    private ByteBuffer input = ByteBuffer.allocate(1024);

    private ByteBuffer output = ByteBuffer.allocate(1024);

    private ExecutorService pool = Executors.newFixedThreadPool(2);

    private static final int READING = 0, SENDING = 1, PROCESSING = 3;

    private int state = READING;

    public MultiThreadHandler(Selector selector, SocketChannel c) throws IOException {
        this.channel = c;
        c.configureBlocking(false);
        //这里注册selector的时候ops设置为0，表示不监听任何事件，当然也可以在这里就设置为监听read
        sk = channel.register(selector, 0);
        sk.attach(this);
        //将感兴趣集设为read，表示此时监听可以read的事件
        //这里不同于前面的serversocketchannel，serversocketchannel是用来监听socket就绪状态的，而这里的socketchannel是用来传输数据的
        sk.interestOps(SelectionKey.OP_READ);
        //wakeup用于唤醒阻塞在select()方法上的线程
        selector.wakeup();

    }

    public static void main(String[] args) {

    }

    boolean inputIsComplete() {
        /* ... */
        return false;
    }

    boolean outputIsComplete() {

        /* ... */
        return false;
    }

    void process() {
        /* ... */
        return;
    }

    /**
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        channel.read(input);
        if (inputIsComplete()) {

            state = PROCESSING;
            //使用线程pool异步执行
            pool.execute(new Processer());
        }
    }

    void send() throws IOException {
        channel.write(output);

        //write完就结束了, 关闭select key
        if (outputIsComplete()) {
            sk.cancel();
        }
    }

//    synchronized void processAndHandOff() {
//        process();
//        state = SENDING;
//        // or rebind attachment
//        //process完,开始等待write就绪
//        sk.interestOps(SelectionKey.OP_WRITE);
//    }

    class Processer implements Runnable {
        public void run() {
            processAndHandOff();
        }

        synchronized void processAndHandOff() {
            process();
            state = SENDING;
            // or rebind attachment
            //process完,开始等待write就绪
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }
}
