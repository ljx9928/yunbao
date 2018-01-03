package case2;

import case5.Server;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class MsgHandler implements Runnable {
    private static LinkedBlockingQueue msgQueue = new LinkedBlockingQueue();
    private Selector selector;
    private static Map<String, Connection> cMap = new HashMap<String, Connection>();

    public MsgHandler(Selector selector) {
        this.selector = selector;

        Thread t = new Thread(this);
        t.setName("MsgHandler");
        t.start();

        //MsgResponder responder = new MsgResponder(msgQueue, selector);
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (selector.select(3000) == 0) {
                    System.out.print(".");
                    continue;
                }
                if (!msgQueue.isEmpty()) {

                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isValid() && key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    if (key.isValid() && key.isWritable()) {
                        handleWrite(key);
                    }
                    if (key.isValid() && key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isValid() && key.isAcceptable()) {
                        handleAccept(key);
                    }

                    it.remove();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void handleAccept(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        try {
            System.out.println("\nAccept connection from ServerSocketChannel " + ssc.toString());
            SocketChannel sc = ssc.accept();
            System.out.println("Get SocketChannel " + sc.toString());
            sc.configureBlocking(false);
            //server 端下发消息
            sc.write(ByteBuffer.wrap(new String("Hi client, I am ready to get your msg.").getBytes()));

            Connection c = new Connection(sc, ByteBuffer.allocateDirect(1024));
            sc.register(key.selector(), SelectionKey.OP_READ, c);


        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static void handleRead(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        try {

            Connection conn = (Connection) key.attachment();

            ByteBuffer byteBuffer = (ByteBuffer) conn.getBuffer();

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
                dispach(sb.toString(), conn);

                byteBuffer.clear();//compact
                bytesRead = sc.read(byteBuffer);

                //write response to channel, send to client
//                ByteBuffer outBuffer = ByteBuffer.wrap(("Got '" + sb + "'").getBytes());
//                sc.write(outBuffer);

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
            System.out.println("write response to " + sc.toString());
            byteBuffer.compact();

            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void dispach(String msg, Connection conn) {
        if (StringUtils.isNotEmpty(msg)) {
            Gson gson = new Gson();
            MsgProtocol mp = gson.fromJson(msg, MsgProtocol.class);

            try {
                conn.setFromUserId(mp.getFromUserId());
                cMap.put(mp.getFromUserId(), conn);
                msgQueue.put(mp);


                MsgProtocol send = (MsgProtocol) msgQueue.peek();


                String toId = send.getToUserId();
                if (cMap.containsKey(toId)) {
                    SocketChannel sc = cMap.get(toId).getSc();

                    sc.write(ByteBuffer.wrap(send.getMsgBody().getBytes()));
                    msgQueue.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
