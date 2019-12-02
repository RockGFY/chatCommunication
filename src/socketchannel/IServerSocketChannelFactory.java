package socketchannel;

import config.Configuration;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;

public interface IServerSocketChannelFactory {

    AsynchronousServerSocketChannel createLocalChannel() throws IOException;

    AsynchronousServerSocketChannel createChannel(Configuration config) throws IOException;

}
