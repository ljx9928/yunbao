package case2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class SocketServer {

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

            while (true) {
                if (selector.select(3000) == 0) {
                    System.out.print(".");
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    if (key.isWritable()) {
                        handleWrite(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleAccept(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        try {
            System.out.println("\nAccept connection from ServerSocketChannel " + ssc.toString());
            SocketChannel sc = ssc.accept();
            System.out.println("Get SocketChannel " + sc.toString());
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static void handleRead(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

            int bytesRead = sc.read(byteBuffer);
            System.out.println("\nReading from SocketChannel " + sc.toString());
            while (bytesRead > 0) {
                byteBuffer.flip();
                StringBuilder sb = new StringBuilder();
                while (byteBuffer.hasRemaining()) {
                    char ch = (char) byteBuffer.get();
                    sb.append(ch);
                    System.out.print(ch);
                }

                System.out.println();
                byteBuffer.clear();//compact
                bytesRead = sc.read(byteBuffer);

                //write response to channel, send to client
                ByteBuffer outBuffer = ByteBuffer.wrap(("Got "+sb).getBytes());
                sc.write(outBuffer);
            }


            if (bytesRead == -1) {
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleWrite(SelectionKey key) {
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

        SocketChannel sc = (SocketChannel) key.channel();
        try {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                sc.write(byteBuffer);
            }
            System.out.println("write response to "+sc.toString());
            byteBuffer.compact();

            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
