package case2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class ClientRead implements Runnable {
    private Selector selector;

    public ClientRead(Selector selector) {
        this.selector = selector;
        Thread t = new Thread(this);
        t.setName("Client read thread.");
        t.start();
    }

    @Override
    public void run() {
        System.out.println("waiting for server's response:");
        try {
            while (true) {

                selector.select(1000);
                for (SelectionKey sk : selector.selectedKeys()) {
                    if (sk.isWritable()) {
                        //sendMsg(selector,sc);
                    }
                    // 如果该SelectionKey对应的Channel中有可读的数据
                    if (sk.isReadable()) {
                        handleRead(sk);
                    }


                    // 删除正在处理的SelectionKey
                    selector.selectedKeys().remove(sk);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void handleRead(SelectionKey key) {
//        try {
//            // 使用NIO读取Channel中的数据
//            SocketChannel ch = (SocketChannel) key.channel();
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            ch.read(buffer);
//            buffer.flip();
//
//            byte[] data = buffer.array();
//            String msg = new String(data).trim();
//            System.out.println("客户端收到信息：" + msg);
//
//            // 为下一次读取作准备
//            key.interestOps(SelectionKey.OP_READ);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void handleRead(SelectionKey key) {
        SocketChannel ch = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            if(buffer==null){
                buffer = ByteBuffer.allocate(1024);
            }
            int bytesRead  = ch.read(buffer);
            while(bytesRead>0) {
                buffer.flip();

                byte[] data = buffer.array();
                String msg = new String(data).trim();
                System.out.println("客户端收到信息：" + msg);

                buffer.clear();//compact
                bytesRead = ch.read(buffer);
            }
            // 为下一次读取作准备
            key.interestOps(SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
