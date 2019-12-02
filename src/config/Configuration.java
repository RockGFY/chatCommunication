package config;

public class Configuration {

    public static String DEFAULT_DIR = "config/";
    public static String REMOTE_SERVER_CONFIG = "remote_config.json";
    public static String LOCAL_SERVER_CONFIG = "local_config.json";

    private String hostName;
    private int port;
    private int timeOut;

    public static Configuration create(String host, int port) {
        return new Configuration(host, port, 10);
    }

    private Configuration() {
    }

    private Configuration(String hostName, int port, int timeOut) {
        this.hostName = hostName;
        this.port = port;
        this.timeOut = timeOut;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
