package util;

import com.alibaba.fastjson.JSON;
import protocol.communication.Packet;
import protocol.entity.Message;
import protocol.entity.User;
import protocol.entity.UserIP;
import protocol.entity.UserRelation;

import java.util.List;

public class DataParser {

    public synchronized static String getValue(String jsonString, String key) {
        return JSON.parseObject(jsonString).getString(key);
    }

    public synchronized static Packet getPacket(String jsonString) {
        return getObjectFromStr(jsonString, Packet.class);
    }

    public synchronized static User getUser(String jsonString) {
        return getObjectFromStr(jsonString, User.class);
    }

    public synchronized static UserIP getUserIP(String jsonString) {
        return getObjectFromStr(jsonString, UserIP.class);
    }

    public synchronized static Message getMessage(String jsonString) {
        return getObjectFromStr(jsonString, Message.class);
    }

    public synchronized static UserRelation getUserRelation(String jsonString) {
        return getObjectFromStr(jsonString, UserRelation.class);
    }

    public synchronized static List<UserIP> getUserIPList(String jsonString) {
        return getListFromStr(jsonString, UserIP.class);
    }

    public synchronized static String parseObjectToStr(Object object) {
        return JSON.toJSONString(object);
    }

    private synchronized static <T> T getObjectFromStr(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    private synchronized static <T> List<T> getListFromStr(String jsonString, Class<T> clazz) {
        return JSON.parseArray(jsonString, clazz);
    }
}


