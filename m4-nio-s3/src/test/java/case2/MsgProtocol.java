package case2;

public class MsgProtocol {
    private String fromUserId;
    private String toUserId;
    private String status;
    private String msgHead;
    private String msgBody;


    public MsgProtocol(String fromUserId, String toUserId, String msgBody) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.msgBody = msgBody;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsgHead() {
        return msgHead;
    }

    public void setMsgHead(String msgHead) {
        this.msgHead = msgHead;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    @Override
    public String toString() {
        return "fromUserId:" + fromUserId + ",toUserId:" + toUserId + ",msgBody:" + msgBody;
    }
}
