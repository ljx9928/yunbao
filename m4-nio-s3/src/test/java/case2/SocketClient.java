package case2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class SocketClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient();
    }


    public SocketClient() {
        try {
            Selector selector = Selector.open();

            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8050);
            sc.connect(socketAddress);
            sc.register(selector, SelectionKey.OP_READ);

            new ClientSend(sc);

            new ClientRead(selector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
