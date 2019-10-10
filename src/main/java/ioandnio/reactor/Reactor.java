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


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Iterator it=selector.selectedKeys().iterator();
                while (it.hasNext()){

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void dispatch(SelectionKey key){
        Runnable runnable=(Runnable) key.attachment();
        runnable!=null?runnable.run():return;
    }

    // inner class
    class Acceptor implements Runnable {
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
//                if (channel != null)
//                    new Handler(selector, channel);
            } catch (IOException ex) { /* ... */ }
        }
    }


}
