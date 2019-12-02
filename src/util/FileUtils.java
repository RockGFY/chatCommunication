package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONWriter;
import config.Configuration;
import protocol.entity.Message;

import javax.xml.xpath.XPath;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * A file utility class.
 */
public class FileUtils {

    public static String readChatLog(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void writeChatLog(String filePath, String userName, String content) throws IOException {
        Path path = Paths.get(filePath);
        try (var writer = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            writer.write(
                    String.format("[ %s ] %s \n\n",
                            userName,
                            content)
            );
        }
    }

    public static void appendToChatLog(String filePath, String userName, String content) throws IOException {
        Path path = Paths.get(filePath);
        try (var writer = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write(
                    String.format("[ %s ] %s \n\n",
                            userName,
                            content)
            );
        }
    }

    public static Configuration readFromConfigFile(String filePath) throws IOException {
        return JSON.parseObject(
                new String(Files.readAllBytes(Paths.get(filePath))),
                Configuration.class);
    }

    public static void writeToConfigFile(String filePath, Configuration config) throws IOException {
        Path path = Paths.get(filePath);
        var writer = new JSONWriter(Files.newBufferedWriter(path));
        writer.writeObject(config);
        writer.flush();
        writer.close();
    }

    public static boolean isExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    public static void createDirectory(String path) throws IOException {
        var dir = Paths.get(path);
        if (!Files.exists(dir))
            Files.createDirectory(dir);
    }
}
