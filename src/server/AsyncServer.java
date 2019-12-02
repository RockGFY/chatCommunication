package server;

import config.Configuration;
import queue.IExecutor;
import server.handler.AcceptHandler;
import socketchannel.IServerSocketChannelFactory;
import util.SystemLogger;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.logging.Level;

public final class AsyncServer
        extends AbstractServer
        implements IServer<AsyncServer> {

    private IServerSocketChannelFactory serverSocketChannelFactory;
    private AsynchronousServerSocketChannel serverSocketChannel;
    private IExecutor executor;

    public AsyncServer(IServerSocketChannelFactory serverSocketChannelFactory) {
        this.serverSocketChannelFactory = serverSocketChannelFactory;
    }

    /**
     * Setup the server by creating a server socket channel
     *
     * @param config The configuration of the socket channel
     * @return A NioServer instance
     */
    @Override
    public AsyncServer setup(Configuration config) {

        try {
            serverSocketChannel = serverSocketChannelFactory.createChannel(config);
        } catch (IOException e) {
            SystemLogger.log(Level.SEVERE, e.getMessage());
        }

        return this;
    }

    @Override
    public AsyncServer setExecutor(IExecutor executor) {
        this.executor = executor;
        return this;
    }

    @Override
    public void start() {
        new Thread(this).start();
        new Thread(executor).start();
    }

    @Override
    public void run() {

        SystemLogger.info("Starting server...");

        serverSocketChannel.accept(serverSocketChannel, new AcceptHandler());
    }

    private void clean() {
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
