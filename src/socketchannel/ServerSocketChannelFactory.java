package socketchannel;

import config.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class ServerSocketChannelFactory
        implements IServerSocketChannelFactory {

    @Override
    public AsynchronousServerSocketChannel createLocalChannel() throws IOException {
        return AsynchronousServerSocketChannel
                .open()
                .bind(new InetSocketAddress(
                        "localhost",
                        8060));
    }

    @Override
    public AsynchronousServerSocketChannel createChannel(Configuration config) throws IOException {
        return AsynchronousServerSocketChannel
                .open()
                .bind(new InetSocketAddress(
                        config.getHostName(),
                        config.getPort()));
    }
}
