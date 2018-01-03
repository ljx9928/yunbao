package case2;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SocketServer {
    public static final Log LOG = LogFactory.getLog(SocketServer.class);



    public static void main(String[] args) {
        selector();
    }

    public static void selector() {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel sc = ServerSocketChannel.open();
            sc.socket().bind(new InetSocketAddress("localhost", 8050));
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_ACCEPT);

            System.out.print("ready to accept connections");
            MsgHandler handler = new MsgHandler(selector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
