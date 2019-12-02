package session;

import server.handler.ReadHandler;
import server.handler.WriteHandler;
import util.CharCoder;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.CharacterCodingException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A session that holds a connection with a specific client through a AsynchronousChannel.
 * All sessions should be put into SessionMap for later queries.
 */
public class Session
        implements ISession {

    private final AsynchronousSocketChannel socketChannel;
    private long lastActiveTime;

    private final Queue<ByteBuffer> queue = new LinkedList<>();
    private boolean isWriting = false;

    private ReadHandler readHandler;
    private WriteHandler writeHandler;

    /**
     * Create a session that holds a protocol.communication channel with the client.
     *
     * @param socketChannel The corresponding client channel
     * @return An established session
     */
    public static Session createSession(AsynchronousSocketChannel socketChannel) {
        return new Session(socketChannel);
    }

    private Session(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        lastActiveTime = System.currentTimeMillis();

        readHandler = new ReadHandler(this);
        writeHandler = new WriteHandler(this);
    }

    public long getTimeElapsed() {
        return System.currentTimeMillis() - lastActiveTime;
    }

    @Override
    public void read(ByteBuffer buffer) {
        lastActiveTime = System.currentTimeMillis();
        socketChannel.read(buffer, buffer, readHandler);
    }

    @Override
    public void write(ByteBuffer buffer) {
        boolean canWrite = false;

        synchronized (queue) {
            queue.add(buffer);

            if (!isWriting) {
                isWriting = true;
                canWrite = true;
            }
        }

        if (canWrite) {
            writeFromQueue();
        }
    }

    public void writeFromQueue() {
        ByteBuffer buffer;
        synchronized (queue) {
            buffer = queue.poll();
            if (buffer == null) {
                isWriting = false;
            }
        }

        if (isWriting) {
            lastActiveTime = System.currentTimeMillis();
            socketChannel
                    .write(buffer, buffer, writeHandler);
        }
    }

    public void write(String data) {
        try {
            var buffer = CharCoder.getBufferFromString(data);
            write(buffer);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }

    public SocketAddress getSocketAddress() {
        try {
            return socketChannel.getRemoteAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
