package queue;

import protocol.communication.Packet;
import protocol.entity.Message;
import protocol.entity.User;
import protocol.entity.UserIP;
import protocol.entity.UserRelation;
import session.Session;
import util.DataParser;

public class Bundle {

    private String rawData;
    private Session session;

    public static Bundle createBundle(String data, Session session) {
        return new Bundle(data, session);
    }

    private Bundle(String rawData, Session session) {
        this.rawData = rawData;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public String getRawData() {
        return rawData;
    }

    public Packet getPacket() {
        return DataParser.getPacket(rawData);
    }

    public User getUser() {
        return DataParser.getUser(getPacket().getAttachment());
    }

    public UserIP getUserIP() {
        return DataParser.getUserIP(getPacket().getAttachment());
    }

    public Message getMessage() {
        return DataParser.getMessage(getPacket().getAttachment());
    }

    public UserRelation getUserRelation() {
        return DataParser.getUserRelation(getPacket().getAttachment());
    }

}
