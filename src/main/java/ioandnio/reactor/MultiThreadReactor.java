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
public class MultiThreadReactor {


    //subReactors集合, 一个selector代表一个subReactor
    SubReactor[] subReactors = new SubReactor[2];
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

        subReactors[0] = new SubReactor(selectors[0]);
        subReactors[1] = new SubReactor(selectors[1]);


    }

    public void start() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    public static void main(String[] args) throws IOException {
        new MultiThreadReactor(9999).start();
    }


    private void dispatch(SelectionKey key) {
        Runnable runnable = (Runnable) key.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    class SubReactor implements Runnable {

        final Selector selector;

        SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Iterator it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        //将消息分发至每个有消息的通道
                        dispatch((SelectionKey) it.next());
                    }
                    selector.selectedKeys().clear();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
