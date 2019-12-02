package server.handler;

import protocol.communication.AbstractPacket;
import session.Session;
import util.SystemLogger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler
        implements CompletionHandler<AsynchronousSocketChannel,
                                        AsynchronousServerSocketChannel> {
    @Override
    public void completed(AsynchronousSocketChannel socketChannel, AsynchronousServerSocketChannel attachment) {

        try {
            SystemLogger.print("New incoming connection: {0}",
                    socketChannel.getRemoteAddress().toString());

            process(socketChannel);

            attachment.accept(attachment, this);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void process(AsynchronousSocketChannel socketChannel) {
        var session = Session.createSession(socketChannel);
        session.read(ByteBuffer.allocate(AbstractPacket.MAX_SIZE));
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
        exc.printStackTrace();
    }
}
