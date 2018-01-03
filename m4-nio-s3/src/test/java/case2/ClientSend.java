package case2;

import com.google.gson.Gson;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.util.BeanUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
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

                Scanner in = new Scanner(System.in);
                String command = null;

                while (true) {
                    System.out.println("Please Enter Command/Msg :");
                    command = in.nextLine();
                    if (command.equals("quit")) {
                        System.out.println("Program Quit!");
                        System.exit(0);
                    } else {
                        //TimeUnit.SECONDS.sleep(5);

                        String info = composeMsg(command);
                        byteBuffer.clear();
                        System.out.println("Client is writing info [" + info + "] to channel. " + sc.toString());
                        byteBuffer.put(info.getBytes());
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            sc.write(byteBuffer);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String composeMsg(String command) {
        String[] ar =  StringUtils.split(command,':');
        MsgProtocol protocol = new MsgProtocol(ar[0],ar[1],ar[2]);
        Gson gson = new Gson();
        return gson.toJson(protocol);
    }

//    public static void main(String[] args) {
//
//        ClientSend c= new ClientSend(null);
//        System.out.println(c.composeMsg("test"));
//
//    }
}
