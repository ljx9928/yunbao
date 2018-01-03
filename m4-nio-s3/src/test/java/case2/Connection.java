package case2;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Connection {
    private SocketChannel sc;
    private String fromUserId;
    private ByteBuffer buffer;

    public Connection(SocketChannel sc,ByteBuffer buffer) {
        this.sc = sc;
        this.buffer=buffer;
    }

    public SocketChannel getSc() {
        return sc;
    }

    public void setSc(SocketChannel sc) {
        this.sc = sc;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }
}
