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
public class Reactor implements Runnable {


    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor());

    }

    public static void main(String[] args) throws IOException {
        new Reactor(9999).run();
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


    private void dispatch(SelectionKey key) {
        Runnable runnable = (Runnable) key.attachment();
        if (runnable != null) {
            //这里用的是runnable.run()，所以是直接调用run方法，并没有开启新的线程
            runnable.run();
        }
    }

    class Acceptor implements Runnable {
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null)
                    new Handler(selector, channel);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
