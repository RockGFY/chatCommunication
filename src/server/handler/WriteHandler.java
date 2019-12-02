package server.handler;

import session.Session;
import util.SystemLogger;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

public class WriteHandler
        implements CompletionHandler<Integer, ByteBuffer> {

    private Session session;

    public WriteHandler(Session session) {
        this.session = session;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (result < 0)
        {
            session.close();
            SystemLogger.info("Client disconnected...");
        }
        else {
            if (buffer.hasRemaining())
                session.write(buffer);
            else
                session.writeFromQueue();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        session.close();
    }
}
