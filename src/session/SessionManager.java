package session;

import com.google.common.collect.HashBiMap;
import util.SocketAddressParser;
import util.SystemLogger;

import java.util.Objects;
import java.util.Set;

/**
 * A singleton manager that keep track of all live sessions.
 * This maintaining a map between the client id and its session.
 */
public class SessionManager {

    private static HashBiMap<String, Session> sessionMap;
    private static SessionManager instance = null;

    public static String SERVER_NAME = "*Server*";

    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null)
                    instance = new SessionManager();
            }
        }
        return instance;
    }

    private SessionManager() {
        sessionMap = HashBiMap.create();
    }

    /**
     * Add a session
     * @param key The associated client id
     * @param session The session that is being added
     */
    public void addSession(String key, Session session) {
        sessionMap.put(key, session);
        SystemLogger.info("{0} is added.\n SessionMap Size: {1}", key, getSize());
    }

    /**
     * Get a session by client id
     * @param key The client id
     * @return The session that associates with the client id
     */
    public Session getSession(String key) {
        return sessionMap.get(key);
    }

    public String getUser(Session session) {
        return sessionMap.inverse().get(session);
    }

    public Set<Session> getAllSessions() {
        return sessionMap.values();
    }

    public Set<String> getAllUsers() {
        return sessionMap.keySet();
    }

    public String getSessionAddress(String key) {
        var socketAddress = Objects.requireNonNull(sessionMap.get(key)).getSocketAddress();
        return SocketAddressParser.getAddress(socketAddress);
    }

    public int getSize() {
        return sessionMap.size();
    }

    public boolean containsUser(String key) {
        return sessionMap.containsKey(key);
    }

    public boolean containsSession(Session session) {
        return sessionMap.containsValue(session);
    }

    public void removeSession(String key) {
        sessionMap.remove(key);
        SystemLogger.info("{0} is removed.\n SessionMap Size: {1}", key, getSize());
    }

    public void removeSession(Session session) {
        sessionMap.inverse().remove(session);
        SystemLogger.info("{0} is removed.\n SessionMap Size: {1}", session, getSize());
    }
}
