package case2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ClientSend implements Runnable {
    private SocketChannel sc;

    public ClientSend(SocketChannel sc) {
        this.sc = sc;
        Thread t = new Thread(this);
        t.setName("Client send thread.");
        t.start();
    }


    @Override
    public void run() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            if (sc.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(5);
                    String info = "I'm " + i++ + "-th information from client.";
                    byteBuffer.clear();
                    System.out.println("Client is writing info [" + info + "] to channel. " + sc.toString());
                    byteBuffer.put(info.getBytes());
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        sc.write(byteBuffer);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
