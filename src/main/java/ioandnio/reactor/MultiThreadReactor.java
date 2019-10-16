package ioandnio.reactor;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author : ybyao
 * @Create : 2019-10-09 17:28
 */
public class MultiThreadReactor implements Runnable {


    //subReactors集合, 一个selector代表一个subReactor
    Selector[] selectors = new Selector[2];
    int next = 0;
    private final ServerSocketChannel serverSocketChannel;

    public MultiThreadReactor(int port) throws IOException {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        SelectionKey key = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor());

    }

    public static void main(String[] args) throws IOException {
        new MultiThreadReactor(9999).run();
    }


    /**
     * 死循环检查是否有通道有监听的消息，这里监听的是就绪事件
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < 2; i++) {
                    selectors[i].select();
                    Iterator it = selectors[i].selectedKeys().iterator();
                    while (it.hasNext()) {
                        //将消息分发至每个有消息的通道
                        dispatch((SelectionKey) it.next());
                    }
                    selectors[i].selectedKeys().clear();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void dispatch(SelectionKey key) {
        Runnable runnable = (Runnable) key.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    class Acceptor implements Runnable {
        public synchronized void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null)
                    new Handler(selectors[next], channel);
                if (++next == selectors.length)
                    next = 0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }


}
