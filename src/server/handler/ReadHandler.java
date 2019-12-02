package server.handler;

import queue.Bundle;
import queue.BundleQueue;
import session.Session;
import util.CharCoder;
import util.SystemLogger;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.List;

public class ReadHandler
        implements CompletionHandler<Integer, ByteBuffer> {

    private Session session;
    private List<String> responseSegments = new ArrayList<>();

    public ReadHandler(Session session) {
        this.session = session;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (result > 0) {

            buffer.flip();
            String rawData = "";
            try {
                rawData = CharCoder.getStringFromBuffer(buffer);
            } catch (CharacterCodingException e) {
                e.printStackTrace();
            }

            SystemLogger.print(
                        "From: {0}\nData: {1}",
                        session.getSocketAddress().toString(),
                        rawData);

            bufferRestore(buffer);
            processRawData(rawData);

            session.read(buffer);
        }
        else if (result < 0) {
            session.close();
            SystemLogger.info("Client disconnected...");
        }
    }

    private void processRawData(String rawData) {
        String delimiter = "\r\n";
        var index = 0;
        var cur = 0;
        var rawDataLength = rawData.length();

        while (cur < rawDataLength) {
            index = rawData.indexOf(delimiter, cur);

            if (index < 0) {
                responseSegments.add(
                        rawData.substring(cur));
                break;
            } else {
                var data = rawData.substring(cur, index);

                if (!responseSegments.isEmpty()) {
                    data = merge(data);
                    responseSegments.clear();
                }

                BundleQueue.add(
                        Bundle.createBundle(data, session));
            }

            cur = index + delimiter.length();
        }
    }

    private String merge(String data) {
        if (responseSegments.isEmpty())
            return data;

        var sb = new StringBuilder();
        for (var segment : responseSegments) {
            sb.append(segment);
        }

        return sb.append(data).toString();
    }

    private void bufferRestore(ByteBuffer buffer) {
        buffer.limit(buffer.capacity());
        buffer.rewind();
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        session.close();
    }
}
